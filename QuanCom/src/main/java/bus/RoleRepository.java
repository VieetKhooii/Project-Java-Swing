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
            System.out.println("Error while getting Roles in database");
        }
        return list;
    }
}
