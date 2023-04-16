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
}
