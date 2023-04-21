package bus;

import config.MySqlConfig;
import model.Orders;
import model.Roles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    public List<Orders> displayOrdersByStatus(String status){
        List<Orders> list = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String query = "select * from orders o where o.order_status = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,status);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Orders orders = new Orders();
                orders.setId(resultSet.getInt("order_id"));
                orders.setStatus(resultSet.getString("order_status"));
                orders.setOrderDate(resultSet.getDate("order_date"));
                orders.setTotalPrice(resultSet.getInt("tonggia"));
                orders.setUserId(resultSet.getInt("user_id"));
                orders.setStaffId(resultSet.getInt("staff_id"));
                list.add(orders);
            }
        } catch (SQLException e) {
            System.out.println("Error while getting Orders in database");
        }
        return list;
    }

    public int markCompleteOrder(int id){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "update orders o set o.order_status = 'Complete' where o.order_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while change order's status "+e.getMessage());
        }
        return isSuccess;
    }
}
