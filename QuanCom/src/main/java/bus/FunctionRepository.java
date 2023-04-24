package bus;

import config.MySqlConfig;
import model.Functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FunctionRepository {
    public List<Functions> getAllFunction(){
        List<Functions> list = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String query = "select * from functions";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Functions functions = new Functions();
                functions.setId(resultSet.getInt("func_id"));
                functions.setName(resultSet.getString("func_name"));
                functions.setDescription(resultSet.getString("description"));
                list.add(functions);
            }
        } catch (SQLException e) {
            System.out.println("Error while getting functions in database");
        }
        return list;
    }

    public int addFunction(String name, String description){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO functions(func_name, description) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,description);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while add functions "+e.getMessage());
        }
        return isSuccess;
    }

    public int deleteFunction(int id){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "delete from functions f where f.func_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting function "+e.getMessage());
        }
        return isSuccess;
    }

    public int modifyFunction(int id, String name, String description){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "update functions set func_name = ?, description = ? where func_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setString(2,description);
            statement.setInt(3,id);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error modify function "+e.getMessage());
        }
        return isSuccess;
    }
}
