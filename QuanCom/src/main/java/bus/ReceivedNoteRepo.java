package bus;

import config.MySqlConfig;
import model.ReceivedNote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceivedNoteRepo {

    public List<ReceivedNote> getAllReceiving(){
        List<ReceivedNote> list = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String query = "select * from phieuNhap";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ReceivedNote receivedNote = new ReceivedNote();
                receivedNote.setId(resultSet.getInt("phieu_id"));
                receivedNote.setStaffId(resultSet.getInt("staff_id"));
                receivedNote.setSupplierId(resultSet.getInt("sup_id"));
                receivedNote.setTotalPrice(resultSet.getInt("tonggia"));
                receivedNote.setDate(resultSet.getDate("date"));
                list.add(receivedNote);
            }
        } catch (SQLException e) {
            System.out.println("Error while query get all receiving note "+e.getMessage());
        }
        return list;
    }
    public int createReceivedNote(int staffId, int supId, int totalPrice, Date date){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO phieuNhap(staff_id, sup_id, tonggia, date) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,staffId);
            preparedStatement.setInt(2, supId);
            preparedStatement.setInt(3,totalPrice);
            preparedStatement.setDate(4,date);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while add receiving note "+e.getMessage());
        }
        return isSuccess;
    }
    public int modifyReceivedNote(int id, int staffId, int supId, int totalPrice, Date date){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "UPDATE phieuNhap set staff_id = ?, sup_id = ?, " +
                "tonggia = ?, date = ? where phieu_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,staffId);
            preparedStatement.setInt(2, supId);
            preparedStatement.setInt(3,totalPrice);
            preparedStatement.setDate(4,date);
            preparedStatement.setInt(5,id);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while modify receiving note "+e.getMessage());
        }
        return isSuccess;
    }

    public int deleteReceivedNote(int id){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "delete from phieuNhap pn where pn.phieu_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting received note "+e.getMessage());
        }
        return isSuccess;
    }

    public int getTotalPrice(int id){
        int totalPrice=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "select sum(ct.gia) as total\n" +
                "from phieuNhap pn join chitietphieuNhap ct on pn.phieu_id = ct.phieu_id\n" +
                "where pn.phieu_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            totalPrice = resultSet.getInt("total");

        } catch (SQLException e) {
            System.out.println("Error getting total price "+e.getMessage());
        }
        return totalPrice;
    }
}
