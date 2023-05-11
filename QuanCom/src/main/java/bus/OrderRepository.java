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

    // search list
    public List<Orders> searchByOption(String searchTxt, String optSearch, String optSort, String priceFrom, String priceTo
            , java.util.Date dateFrom, java.util.Date dateTo){
        List<Orders> searchList = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String searchString="";
        String searchColumn = "";
        if(optSearch.equalsIgnoreCase("Mã hóa đơn")) {
            searchString = "'%" + searchTxt.trim() + "%'";
            searchColumn = "order_id";
        }
        else if(optSearch.equalsIgnoreCase("Mã nhân viên")) {
            searchString = "'%" + searchTxt.trim() + "%'";
            searchColumn = "staff_id";
        }
        String query = "select * from orders where " + searchColumn +" like " + searchString;
        if(!priceFrom.equals("") && !priceTo.equals("")) {
            query = query + " and tonggia >= " + Integer.parseInt(priceFrom) + " and tonggia <="  + Integer.parseInt(priceTo);
        }
        if(dateFrom != null && dateTo != null) {
            java.sql.Date sqlDateFrom = new Date(dateFrom.getTime());
            java.sql.Date sqlDateTo = new Date(dateTo.getTime());
            query = query + " and order_date between '" + sqlDateFrom + "' and '" + sqlDateTo + "'";
        }
        if(optSort.equalsIgnoreCase("Mã HĐ giảm dần")) {
            query = query + " order by order_id desc";
        }
        else if(optSort.equalsIgnoreCase("Mã NV tăng dần")){
            query = query + " order by staff_id asc";
        }
        else if(optSort.equalsIgnoreCase("Mã NV giảm dần")){
            query = query + " order by staff_id desc";
        }
        else if(optSort.equalsIgnoreCase("Mới nhất")){
            query = query + " order by order_date desc";
        }
        else if(optSort.equalsIgnoreCase("Cũ nhất")){
            query = query + " order by order_date asc";
        }
        else if(optSort.equalsIgnoreCase("Tổng giá tăng dần")){
            query = query + " order by tonggia asc";
        }
        else if(optSort.equalsIgnoreCase("Tổng giá giảm dần")){
            query = query + " order by tonggia desc";
        }
        System.out.println(query);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Orders orders = new Orders();
                orders.setId(resultSet.getInt("order_id"));
                orders.setStatus(resultSet.getString("order_status"));
                orders.setOrderDate(resultSet.getDate("order_date"));
                orders.setTotalPrice(resultSet.getInt("tonggia"));
                orders.setUserId(resultSet.getInt("user_id"));
                orders.setStaffId(resultSet.getInt("staff_id"));
                searchList.add(orders);
            }
        } catch (SQLException e) {
            System.out.println("Error while searching data");
        }
        return searchList;
    }

    public int numberOfBillOfAStaff(int staffId){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "select count(*) as count from orders where staff_id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,staffId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                isSuccess = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("OrderRepository: Error while getting amount of order");
        }
        return isSuccess;
    }

    public int numberOfProductOfStaff(int staffId){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "SELECT COALESCE(SUM(ct.soluong), 0) as total_amount \n" +
                "FROM staffs s \n" +
                "LEFT JOIN orders o ON s.staff_id = o.staff_id \n" +
                "LEFT JOIN chitiet_orders ct ON o.order_id = ct.order_id \n" +
                "WHERE s.staff_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,staffId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                isSuccess = resultSet.getInt("total_amount");
            }
        } catch (SQLException e) {
            System.out.println("OrderRepository: Error while getting amount of product of a staff");
        }
        return isSuccess;
    }

    public int totalPriceOfStaff(int staffId){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "SELECT COALESCE(SUM(ct.gia), 0) as total_price \n" +
                "FROM staffs s \n" +
                "LEFT JOIN orders o ON s.staff_id = o.staff_id \n" +
                "LEFT JOIN chitiet_orders ct ON o.order_id = ct.order_id \n" +
                "WHERE s.staff_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,staffId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                isSuccess = resultSet.getInt("total_price");
            }
        } catch (SQLException e) {
            System.out.println("OrderRepository: Error while getting total price of a staff");
        }
        return isSuccess;
    }
}
