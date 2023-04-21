package bus;

import config.MySqlConfig;
import model.Functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleFuncRepo {

    //Display functions of a role
    public List<Functions> funcOfRole(int roleId){
        List<Functions> list = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String query = "select f.func_name, f.func_id from role_func rf join functions f on" +
                " f.func_id = rf.func_id where rf.role_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,roleId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Functions functions = new Functions();
                functions.setId(resultSet.getInt("func_id"));
                functions.setName(resultSet.getString("func_name"));
                list.add(functions);
            }
        } catch (SQLException e) {
            System.out.println("Error while get function of role with id no."+roleId);
        }
        return list;
    }

    public int addFuncOfRole(int funcId, int roleId){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "insert into role_func(role_id, func_id) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,roleId);
            preparedStatement.setInt(2,funcId);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while add functions no."+funcId +" to role no."+roleId);
        }
        return isSuccess;
    }

    public int deleteAllFuncRole(int roleId){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "delete from role_func rf where  rf.role_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,roleId);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while delete functions from role no."+roleId);
        }
        return isSuccess;
    }

    public int modifyFuncOfRole(int funcId, int roleId){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "update role_func set func_id=? where role_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,funcId);
            preparedStatement.setInt(2,roleId);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while modify functions no."+funcId +" to role no."+roleId);
        }
        return isSuccess;
    }

    public int deleteSpecificFuncRole(int funcId, int roleId){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "delete from role_func rf where rf.role_id=? and rf.func_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,roleId);
            preparedStatement.setInt(2,funcId);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while delete function no."+funcId+" from role no."+roleId);
        }
        return isSuccess;
    }
}
