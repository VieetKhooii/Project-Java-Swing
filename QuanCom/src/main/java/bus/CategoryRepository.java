package bus;

import config.MySqlConfig;
import model.Category;
import model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    public List<Category> getAllCategory(){
        List<Category> list = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String query = "select * from category";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getInt("cate_id"));
                category.setName(resultSet.getString("cate_name"));
                category.setDescription(resultSet.getString("description"));
                list.add(category);
            }
        } catch (SQLException e) {
            System.out.println("Error while getting category in CategoryRepository");
        }
        return list;
    }

    public int addCate(String name, String description){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO category(cate_name, description) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,description);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while adding cate in CategoryRepository "+e.getMessage());
        }
        return isSuccess;
    }

    public int deleteCate(int id){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "delete from category m where m.cate_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting cate in CategoryRepository "+e.getMessage());
        }
        return isSuccess;
    }

    public int modifyCate(int id, String name, String description){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "update category set cate_name = ?, description = ? where cate_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setString(2,description);
            statement.setInt(3,id);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error modifying cate in CategoryRepository "+e.getMessage());
        }
        return isSuccess;
    }
    //search option
    public List<Category> searchByOption(String searchTxt, String optSearch, String optSort){
        List<Category> searchList = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String searchString="";
        String searchColumn = "";
        if(optSearch.equalsIgnoreCase("Mã loại")) {
        	searchString = "'%" + searchTxt.trim() + "%'";
        	searchColumn = "cate_id";
        }
        else if(optSearch.equalsIgnoreCase("Tên loại")) {
        	searchString = "'%" + searchTxt.trim() + "%'";
        	searchColumn = "cate_name";
        }
        String query = "select * from category where " + searchColumn +" like " + searchString;
        if(optSort.equalsIgnoreCase("Mã loại giảm dần")) {
        	query = query + " order by cate_id desc";
        }
        else if(optSort.equalsIgnoreCase("Tên loại")){
        	query = query + " order by cate_name asc";
        }
        System.out.println(query);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
            	Category category = new Category();
                category.setId(resultSet.getInt("cate_id"));
                category.setName(resultSet.getString("cate_name"));
                category.setDescription(resultSet.getString("description"));
                searchList.add(category);
            }
        } catch (SQLException e) {
            System.out.println("Error while searching data");
        }        
        return searchList;
    }
}