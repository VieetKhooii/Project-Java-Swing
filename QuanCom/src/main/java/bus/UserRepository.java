package bus;

import config.MySqlConfig;
import model.Staff;
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
                user.setStaffId(resultSet.getInt("staff_id"));
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error while query get all users "+e.getMessage());
        }       
        return list;
    }

    public int login(String userName, String password){
        int count=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "select count(*) as count from users u where u.username= ? and u.password= ?";
        try {
            PreparedStatement preparedStatement =connection.prepareStatement(query);
            preparedStatement.setString(1,userName);
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
            String email,
            String password,
            int roleId,
            int staffId){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO users(username, password, email, role_id, staff_id)\n" +
                "values\n" +
                "(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,email);
            preparedStatement.setInt(4,roleId);
            preparedStatement.setInt(5,staffId);
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
            String email,
            String password,
            int roleId,
            int staffId){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "update users set username = ?, password = ?, " +
                "email = ?, role_id = ?, staff_id = ? where user_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,email);
            preparedStatement.setInt(4,roleId);
            preparedStatement.setInt(5,staffId);
            preparedStatement.setInt(6,id);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while modify user "+e.getMessage());
        }
        return isSuccess;
    }
    //Search by option
    public List<User> searchByOption(String searchTxt, String optSearch, String optSort){
        List<User> searchList = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String searchString="";
        String searchColumn = "";
        if(optSearch.equalsIgnoreCase("Mã tài khoản")) {
        	searchString = "'%" + searchTxt.trim() + "%'";
        	searchColumn = "user_id";
        }
        else if(optSearch.equalsIgnoreCase("Tên đăng nhập")) {
        	searchString = "'%" + searchTxt.trim() + "%'";
        	searchColumn = "username";
        }
        else if(optSearch.equalsIgnoreCase("Quyền")) {
        	searchString = "'%" + searchTxt.trim() + "%'";
        	searchColumn = "role_name";
        }
        String query = "select u.user_id, u.username, u.password, email, u.role_id, r.role_name from users as u join roles as r on u.role_id = r.role_id and "
        + searchColumn +" like " + searchString;
        if(optSort.equalsIgnoreCase("Mã tài khoản giảm dần")) {
        	query = query + " order by u.user_id desc";
        }
        else if(optSort.equalsIgnoreCase("Tên đăng nhập")){
        	query = query + " order by u.username asc";
        }
        else if(optSort.equalsIgnoreCase("Quyền")){
        	query = query + " order by r.role_name asc";
        }
        System.out.println(query);
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
                user.setStaffId(resultSet.getInt("staff_id"));
                searchList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error while searching data");
        }        
        return searchList;
    }
}
