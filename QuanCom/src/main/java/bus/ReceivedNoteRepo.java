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
            System.out.println("ReceivedNoteRepo: Error while query get all receiving note "+e.getMessage());
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
            System.out.println("ReceivedNoteRepo: Error while add receiving note "+e.getMessage());
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
            System.out.println("ReceivedNoteRepo: Error while modify receiving note "+e.getMessage());
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
            System.out.println("ReceivedNoteRepo: Error deleting received note "+e.getMessage());
        }
        return isSuccess;
    }

    //Search by option
    public List<ReceivedNote> searchByOption(String searchTxt, String optSearch, String optSort, String priceFrom, String priceTo
            , java.util.Date dateFrom, java.util.Date dateTo){
        List<ReceivedNote> searchList = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String searchString="";
        String searchColumn = "";
        if(optSearch.equalsIgnoreCase("Mã phiếu nhập")) {
            searchString = "'%" + searchTxt.trim() + "%'";
            searchColumn = "phieu_id";
        }
        else if(optSearch.equalsIgnoreCase("Mã nhân viên")) {
            searchString = "'%" + searchTxt.trim() + "%'";
            searchColumn = "staff_id";
        }
        else if(optSearch.equalsIgnoreCase("Mã nhà cung cấp")) {
            searchString = "'%" + searchTxt.trim() + "%'";
            searchColumn = "sup_id";
        }
        String query = "select * from phieunhap where " + searchColumn +" like " + searchString;
        if(!priceFrom.equals("") && !priceTo.equals("")) {
            query = query + " and tonggia >= " + Integer.parseInt(priceFrom) + " and tonggia <="  + Integer.parseInt(priceTo);
        }
        if(dateFrom != null && dateTo != null) {
            java.sql.Date sqlDateFrom = new Date(dateFrom.getTime());
            java.sql.Date sqlDateTo = new Date(dateTo.getTime());
            query = query + " and date between '" + sqlDateFrom + "' and '" + sqlDateTo + "'";
        }
        if(optSort.equalsIgnoreCase("Mã PN giảm dần")) {
            query = query + " order by phieu_id desc";
        }
        else if(optSort.equalsIgnoreCase("Mã NV tăng dần")){
            query = query + " order by staff_id asc";
        }
        else if(optSort.equalsIgnoreCase("Mã NV giảm dần")){
            query = query + " order by staff_id desc";
        }
        else if(optSort.equalsIgnoreCase("Mã NCC tăng dần")){
            query = query + " order by sup_id asc";
        }
        else if(optSort.equalsIgnoreCase("Mã NCC giảm dần")){
            query = query + " order by sup_id desc";
        }
        else if(optSort.equalsIgnoreCase("Mới nhất")){
            query = query + " order by date desc";
        }
        else if(optSort.equalsIgnoreCase("Cũ nhất")){
            query = query + " order by date asc";
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
                ReceivedNote receivedNote = new ReceivedNote();
                receivedNote.setId(resultSet.getInt("phieu_id"));
                receivedNote.setStaffId(resultSet.getInt("staff_id"));
                receivedNote.setSupplierId(resultSet.getInt("sup_id"));
                receivedNote.setTotalPrice(resultSet.getInt("tonggia"));
                receivedNote.setDate(resultSet.getDate("date"));
                searchList.add(receivedNote);
            }
        } catch (SQLException e) {
            System.out.println("Error while searching data");
        }
        return searchList;
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
            System.out.println("ReceivedNoteRepo: Error getting total price "+e.getMessage());
        }
        return totalPrice;
    }

    public int totalMaterialAmountOfStaff(int staffId){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "SELECT COALESCE(SUM(ct.soluong), 0) as total_amount\n" +
                "FROM staffs s \n" +
                "LEFT JOIN phieuNhap pn ON s.staff_id = pn.staff_id \n" +
                "LEFT JOIN chitietphieuNhap ct ON pn.phieu_id = ct.phieu_id \n" +
                "WHERE s.staff_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,staffId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                isSuccess = resultSet.getInt("total_amount");
            }
        } catch (SQLException e) {
            System.out.println("ReceiNoteRepo: Error while getting total material amount of a staff");
        }
        return isSuccess;
    }

    public int totalReceiveNoteOfStaff(int staffId){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "select count(*) as count from phieuNhap where staff_id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,staffId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                isSuccess = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("ReceiNoteRepo: Error while getting amount of receive note of a staff");
        }
        return isSuccess;
    }

    public int totalMaterialPriceOfStaff(int staffId){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "SELECT COALESCE(SUM(ct.gia), 0) as total_price\n" +
                "FROM staffs s \n" +
                "LEFT JOIN phieuNhap pn ON s.staff_id = pn.staff_id \n" +
                "LEFT JOIN chitietphieuNhap ct ON pn.phieu_id = ct.phieu_id \n" +
                "WHERE s.staff_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,staffId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                isSuccess = resultSet.getInt("total_price");
            }
        } catch (SQLException e) {
            System.out.println("ReceiNoteRepo: Error while getting total material price of a staff");
        }
        return isSuccess;
    }
}
