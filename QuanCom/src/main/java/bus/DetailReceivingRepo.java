package bus;

import config.MySqlConfig;
import model.ReceivedNoteDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetailReceivingRepo {
    public List<ReceivedNoteDetail> getAllDetailedReceiving(){
        List<ReceivedNoteDetail> list = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String query = "select * from chitietphieuNhap";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ReceivedNoteDetail receivedNoteDetail = new ReceivedNoteDetail();
                receivedNoteDetail.setMaterialId(resultSet.getInt("material_id"));
                receivedNoteDetail.setReceivedNoteId(resultSet.getInt("phieu_id"));
                receivedNoteDetail.setName(resultSet.getString("name"));
                receivedNoteDetail.setAmount(resultSet.getInt("soluong"));
                receivedNoteDetail.setPrice(resultSet.getInt("gia"));
                list.add(receivedNoteDetail);
            }
        } catch (SQLException e) {
            System.out.println("Error while query get all detailed receiving note "+e.getMessage());
        }
        return list;
    }
    public int createDetailedReceivedNote(int materialId, int receivedNoteId, String name, int amount, int price){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO chitietphieuNhap(material_id, phieu_id, name, soluong, gia) " +
                "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,materialId);
            preparedStatement.setInt(2, receivedNoteId);
            preparedStatement.setString(3,name);
            preparedStatement.setInt(4,amount);
            preparedStatement.setInt(5,price);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while adding detailed receiving note "+e.getMessage());
        }
        return isSuccess;
    }

    public int deleteDetailReceivedNote(int id){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "delete from chitietphieuNhap ct where ct.phieu_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting detailed received note "+e.getMessage());
        }
        return isSuccess;
    }
}
