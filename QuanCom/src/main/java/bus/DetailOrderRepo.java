package bus;

import config.MySqlConfig;
import model.DetailOrder;
import model.ReceivedNoteDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetailOrderRepo {

    public List<DetailOrder> getAllDetailOrder(){
        List<DetailOrder> list = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String query = "select * from chitiet_orders";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                DetailOrder detailOrder = new DetailOrder();
                detailOrder.setOrderId(resultSet.getInt("order_id"));
                detailOrder.setProductId(resultSet.getInt("product_id"));
                detailOrder.setName(resultSet.getString("name"));
                detailOrder.setAmount(resultSet.getInt("soluong"));
                detailOrder.setPrice(resultSet.getInt("gia"));
                list.add(detailOrder);
            }
        } catch (SQLException e) {
            System.out.println("DetailOrderRepo: Error while getting all detail order "+e.getMessage());
        }
        return list;
    }

    public int addDetailOrder(int orderId, int productId, String name, int amount, int price){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "insert into chitiet_orders(order_id, product_id, name, soluong, gia) values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2,productId);
            preparedStatement.setString(3,name);
            preparedStatement.setInt(4,amount);
            preparedStatement.setInt(5,price);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("OrderRepository: Error while adding detail order "+e.getMessage());
        }
        return isSuccess;
    }

    public int delDetailOrder(int orderId){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "delete from chitiet_orders ct where ct.order_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,orderId);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DetailOrderRepo: Error while deleting detail order");
        }
        return  isSuccess;
    }
}