package bus;

import config.MySqlConfig;
import model.Roles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    public List<Roles> getAllRole(){
        List<Roles> list = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String query = "select * from roles";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Roles roles = new Roles();
                roles.setId(resultSet.getInt("role_id"));
                roles.setName(resultSet.getString("role_name"));
                roles.setDescription(resultSet.getString("description"));
                list.add(roles);
            }
        } catch (SQLException e) {
            System.out.println("RoleRepository: Error while getting Roles in database");
        }
        return list;
    }

    public int addRole(String roleName, String description){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO roles(role_name, description) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,roleName);
            preparedStatement.setString(2,description);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("RoleRepository: Error while add role "+e.getMessage());
        }
        return isSuccess;
    }

    public int deleteRole(int id){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "delete from roles r where r.role_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("RoleRepository: Error deleting role "+e.getMessage());
        }
        return isSuccess;
    }

    public int modifyRole(int id, String roleName, String description){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "update roles set role_name = ?, description = ? where role_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,roleName);
            statement.setString(2,description);
            statement.setInt(3,id);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("RoleRepository: Error modify role "+e.getMessage());
        }
        return isSuccess;
    }


}
