package repository;

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
}
