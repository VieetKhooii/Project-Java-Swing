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
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error while query get all users "+e.getMessage());
        }
        return list;
    }

    public int login(String email, String password){
        int count=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "select count(*) as count from users u where u.email= ? and u.password= ?";
        try {
            PreparedStatement preparedStatement =connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()){
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Error login query "+e.getMessage());
        }
        return count;
    }

    public int addUser(
            String userName,
            String fullName,
            String email,
            String password,
            String address,
            String phone,
            int roleId){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO users(username, password, fullname, email, address, phonenumber, role_id)\n" +
                "values\n" +
                "(?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,fullName);
            preparedStatement.setString(4,email);
            preparedStatement.setString(5,address);
            preparedStatement.setString(6,phone);
            preparedStatement.setInt(7,roleId);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while add user "+e.getMessage());
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
            System.out.println("Error deleting user "+e.getMessage());
        }
        return isSuccess;
    }

    public int modifyUser(
            int id,
            String userName,
            String fullName,
            String email,
            String password,
            String address,
            String phone,
            int roleId){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "update users set email = ? , password = ? , fullname = ? , role_id = ? where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,fullName);
            preparedStatement.setString(4,email);
            preparedStatement.setString(5,address);
            preparedStatement.setString(6,phone);
            preparedStatement.setInt(7,roleId);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while add user "+e.getMessage());
        }
        return isSuccess;
    }
}
