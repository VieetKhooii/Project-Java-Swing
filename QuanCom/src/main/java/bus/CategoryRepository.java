package bus;

import config.MySqlConfig;
import model.Category;

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
            System.out.println("Error while getting category in database");
        }
        return list;
    }

    public int addMutualCate(String name, String description){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO mutual_category(mutual_name, description) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,description);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while add mutual cate "+e.getMessage());
        }
        return isSuccess;
    }

    public int deleteMutualCate(int id){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "delete from mutual_category m where m.mutual_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting mutual cate "+e.getMessage());
        }
        return isSuccess;
    }

    public int modifyMutualCate(int id, String name, String description){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "update mutual_category set mutual_name = ?, description = ? where mutual_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setString(2,description);
            statement.setInt(3,id);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error modify mutual cate "+e.getMessage());
        }
        return isSuccess;
    }
}
