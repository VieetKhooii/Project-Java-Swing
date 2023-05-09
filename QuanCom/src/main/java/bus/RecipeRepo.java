package bus;

import config.MySqlConfig;
import model.Recipe;
import model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeRepo {
    public List<Recipe> getAllRecipe(){
        List<Recipe> list = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String query = "select * from chitietcongthuc";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Recipe recipe = new Recipe();
                recipe.setProductId(resultSet.getInt("product_id"));
                recipe.setMaterialId(resultSet.getInt("material_id"));
                recipe.setAmount(resultSet.getInt("soluong"));
                list.add(recipe);
            }
        } catch (SQLException e) {
            System.out.println("RecipeRepo: Error while getting recipe in database");
        }
        return list;
    }

    public int addRecipe(int productId, int materialId, int amount){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO chitietcongthuc(product_id, material_id, soluong) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,productId);
            preparedStatement.setInt(2,materialId);
            preparedStatement.setInt(3,amount);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("RecipeRepo: Error while adding recipe "+e.getMessage());
        }
        return isSuccess;
    }

    public int deleteRecipe(int productId, int materialId){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "delete from chitietcongthuc ct where ct.product_id = ? and ct.material_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,productId);
            statement.setInt(2,materialId);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("RecipeRepo: Error deleting recipe "+e.getMessage());
        }
        return isSuccess;
    }

    public int modifyRecipe(int productId, int materialId, int amount, int oldProduct, int oldMaterial){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "update chitietcongthuc set product_id = ?, material_id = ?, soluong = ? where " +
                "product_id = ? and material_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,productId);
            statement.setInt(2,materialId);
            statement.setInt(3,amount);
            statement.setInt(4,oldProduct);
            statement.setInt(5,oldMaterial);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("RecipeRepo: Error modify recipe "+e.getMessage());
        }
        return isSuccess;
    }
    
    //Search by option
    public List<Recipe> searchByOption(String searchTxt, String optSearch, String optSort){
        List<Recipe> searchList = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String searchString="";
        String searchColumn = "";
        if(optSearch.equalsIgnoreCase("Mã món")) {
        	searchString = "'%" + searchTxt.trim() + "%'";
        	searchColumn = "product_id";
        }
        else if(optSearch.equalsIgnoreCase("Mã ng.liệu")) {
        	searchString = "'%" + searchTxt.trim() + "%'";
        	searchColumn = "material_id";
        }
        String query = "select * from chitietcongthuc where " + searchColumn +" like " + searchString;
        if(optSort.equalsIgnoreCase("Mã món tăng dần")) {
        	query = query + " order by product_id asc";
        }
        if(optSort.equalsIgnoreCase("Mã món giảm dần")) {
        	query = query + " order by product_id desc";
        }
        else if(optSort.equalsIgnoreCase("Mã ng.liệu tăng dần")){
        	query = query + " order by material_id asc";
        }
        else if(optSort.equalsIgnoreCase("Mã ng.liệu giảm dần")){
        	query = query + " order by material_id desc";
        }
        System.out.println(query);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
            	Recipe recipe = new Recipe();
                recipe.setProductId(resultSet.getInt("product_id"));
                recipe.setMaterialId(resultSet.getInt("material_id"));
                recipe.setAmount(resultSet.getInt("soluong"));
                searchList.add(recipe);
            }
        } catch (SQLException e) {
            System.out.println("Error while searching data");
        }        
        return searchList;
    }
}