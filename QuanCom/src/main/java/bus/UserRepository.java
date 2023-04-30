package bus;

import config.MySqlConfig;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public List<User> getAllUser(){
        List<User> list = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String query = "select * from users";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRoleId(resultSet.getInt("role_id"));
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("UserRepository: Error while query get all users "+e.getMessage());
        }
        return list;
    }

    public int login(String name, String password){
        int count=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "select count(*) as count from users u where u.username= ? and u.password= ?";
        try {
            PreparedStatement preparedStatement =connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,password);
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()){
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("UserRepository: Error login query "+e.getMessage());
        }
        return count;
    }

    public int addUser(
            String userName,
            String email,
            String password,
            int roleId){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO users(username, password, email, role_id)\n" +
                "values\n" +
                "(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,email);
            preparedStatement.setInt(4,roleId);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("UserRepository: Error while add user "+e.getMessage());
        }
        return isSuccess;
    }

    public int deleteUser(int id){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "delete from users u where u.user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("UserRepository: Error deleting user "+e.getMessage());
        }
        return isSuccess;
    }

    public int modifyUser(
            int id,
            String userName,
            String email,
            String password,
            int roleId){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "update users set username = ?, password = ?, " +
                "email = ?, role_id = ? where user_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,email);
            preparedStatement.setInt(4,roleId);
            preparedStatement.setInt(5,id);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("UserRepository: Error while modify user "+e.getMessage());
        }
        return isSuccess;
    }
}
