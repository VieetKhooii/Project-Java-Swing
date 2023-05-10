package bus;

import config.MySqlConfig;
import model.Orders;
import model.Roles;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    public List<Orders> displayOrders(){
        List<Orders> list = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String query = "select * from orders o";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
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
            System.out.println("OrderRepository: Error while getting Orders in database");
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
            System.out.println("OrderRepository: Error while change order's status "+e.getMessage());
        }
        return isSuccess;
    }

    public int addOrder(int staffId, int totalPrice, Date date){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "insert into orders(staff_id, tonggia, order_date) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, staffId);
            preparedStatement.setInt(2,totalPrice);
            preparedStatement.setDate(3,date);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("OrderRepository: Error while adding order"+e.getMessage());
        }
        return isSuccess;
    }
    public int delOrder(int orderId){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "delete from orders o where o.order_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,orderId);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("OrderRepository: Error while deleting order");
        }
        return  isSuccess;
    }

    public int numberOfBillOfAStaff(int staffId){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "select count(*) as count from orders where staff_id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,staffId);
            ResultSet resultSet = preparedStatement.executeQuery();
            isSuccess = resultSet.getInt("count");
        } catch (SQLException e) {
            System.out.println("OrderRepository: Error while deleting order");
        }
        return isSuccess;
    }
}
