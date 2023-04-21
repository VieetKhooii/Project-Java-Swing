package bus;

import config.MySqlConfig;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReceivedNoteRepo {
    public int createReceivedNote(int staffId, int supId, int totalPrice, Date date){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO phieuNhap(staff_id, sup_id, tonggia, date) VALUES (?,?,?,?)";
        String queryDetail = "INSERT INTO chitietphieuNhap()";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,staffId);
            preparedStatement.setInt(2, supId);
            preparedStatement.setInt(3,totalPrice);
            preparedStatement.setDate(4,date);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while add product "+e.getMessage());
        }
        return isSuccess;
    }
}
