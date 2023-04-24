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
}
