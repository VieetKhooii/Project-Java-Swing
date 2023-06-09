package bus;

import config.MySqlConfig;
import model.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialRepository {
    public List<Material> getAllMaterial(){
        List<Material> list = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String query = "select * from materials";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Material material = new Material();
                material.setId(resultSet.getInt("material_id"));
                material.setName(resultSet.getString("name"));
                material.setUnit(resultSet.getString("donvitinh"));
                material.setPrice(resultSet.getInt("gia"));
                material.setAmount(resultSet.getInt("soluong"));
                list.add(material);
            }
        } catch (SQLException e) {
            System.out.println("Error while getting material in database");
        }
        return list;
    }

    public int addMaterial(String name, String unit, int price, int amount){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO materials(name, donvitinh, gia, soluong) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,unit);
            preparedStatement.setInt(3,price);
            preparedStatement.setInt(4,amount);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while adding material "+e.getMessage());
        }
        return isSuccess;
    }
    public int addMaterial(int id, String name, String unit, int price, int amount){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO materials(material_id, name, donvitinh, gia, soluong) values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,unit);
            preparedStatement.setInt(4,price);
            preparedStatement.setInt(5,amount);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while adding material "+e.getMessage());
        }
        return isSuccess;
    }
    public int deleteMaterial(int id){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "delete from materials m where m.material_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting material "+e.getMessage());
        }
        return isSuccess;
    }

    public int modifyMaterial(int id, String name, String unit, int price, int amount){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "update materials set name = ?, donvitinh = ?, gia = ?, soluong = ?" +
                " where material_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setString(2,unit);
            statement.setInt(3,price);
            statement.setInt(4,amount);
            statement.setInt(5,id);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error modify material "+e.getMessage());
        }
        return isSuccess;
    }
    //Search option
    public List<Material> searchByOption(String searchTxt, String optSearch, String optSort){
        List<Material> searchList = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String searchString="";
        String searchColumn = "";
        if(optSearch.equalsIgnoreCase("Mã")) {
            searchString = "'%" + searchTxt.trim() + "%'";
            searchColumn = "material_id";
        }
        else if(optSearch.equalsIgnoreCase("Tên")) {
            searchString = "'%" + searchTxt.trim() + "%'";
            searchColumn = "name";
        }
        else if(optSearch.equalsIgnoreCase("Số lượng")) {
            searchString = "'%" + searchTxt.trim() + "%'";
            searchColumn = "soluong";
        }
        String query = "select * from materials where " + searchColumn +" like " + searchString;
        if(optSort.equalsIgnoreCase("Mã giảm dần")) {
            query = query + " order by material_id desc";
        }
        else if(optSort.equalsIgnoreCase("Tên")){
            query = query + " order by name asc";
        }
        else if(optSort.equalsIgnoreCase("Giá tăng dần")){
            query = query + " order by gia asc";
        }
        else if(optSort.equalsIgnoreCase("Giá giảm dần")){
            query = query + " order by gia desc";
        }
        else if(optSort.equalsIgnoreCase("Số lượng tăng dần")){
            query = query + " order by soluong asc";
        }
        else if(optSort.equalsIgnoreCase("Số lượng giảm dần")){
            query = query + " order by soluong desc";
        }
        System.out.println(query);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Material material = new Material();
                material.setId(resultSet.getInt("material_id"));
                material.setName(resultSet.getString("name"));
                material.setUnit(resultSet.getString("donvitinh"));
                material.setPrice(resultSet.getInt("gia"));
                material.setAmount(resultSet.getInt("soluong"));
                searchList.add(material);
            }
        } catch (SQLException e) {
            System.out.println("Error while searching data");
        }
        return searchList;
    }

    public int totalMaterialReceived(int materialId){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "SELECT COALESCE(SUM(ct.soluong), 0) as total_amount\n" +
                "FROM materials m \n" +
                "LEFT JOIN chitietphieuNhap ct ON m.material_id = ct.material_id\n" +
                "WHERE m.material_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,materialId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                isSuccess = resultSet.getInt("total_amount");
            }
        } catch (SQLException e) {
            System.out.println("MaterialRepository: Error while getting total receive amount of a material");
        }
        return isSuccess;
    }

    public int totalReceivePriceOfAMaterial(int materialId){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "SELECT COALESCE(SUM(ct.gia), 0) as total_price\n" +
                "FROM materials m \n" +
                "LEFT JOIN chitietphieuNhap ct ON m.material_id = ct.material_id\n" +
                "WHERE m.material_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,materialId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                isSuccess = resultSet.getInt("total_price");
            }
        } catch (SQLException e) {
            System.out.println("MaterialRepository: Error while getting total receive price of a material");
        }
        return isSuccess;
    }



}
