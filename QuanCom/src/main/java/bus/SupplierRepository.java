package bus;

import config.MySqlConfig;
import model.Roles;
import model.Staff;
import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepository {
    public List<Supplier> getAllSupplier(){
        List<Supplier> list = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String query = "select * from supplier";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Supplier supplier = new Supplier();
                supplier.setId(resultSet.getInt("sup_id"));
                supplier.setName(resultSet.getString("sup_name"));
                supplier.setAddress(resultSet.getString("sup_address"));
                supplier.setPhone(resultSet.getString("supp_phone"));
                list.add(supplier);
            }
        } catch (SQLException e) {
            System.out.println("Error while getting Supplier in database");
        }
        return list;
    }

    public int addSupplier(String name, String address, String phone){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO supplier(sup_name, sup_address, supp_phone) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,address);
            preparedStatement.setString(3,phone);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while add supplier "+e.getMessage());
        }
        return isSuccess;
    }

    public int deleteSupplier(int id){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "delete from supplier s where s.sup_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting supplier "+e.getMessage());
        }
        return isSuccess;
    }

    public int modifySupplier(int id, String name, String address, String phone){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "update supplier set sup_name = ?, sup_address = ?,  supp_phone = ? where sup_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setString(2,address);
            statement.setString(3,phone);
            statement.setInt(4,id);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error modify supplier "+e.getMessage());
        }
        return isSuccess;
    }
    
    //Search by option
    public List<Supplier> searchByOption(String searchTxt, String optSearch, String optSort){
        List<Supplier> searchList = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String searchString="";
        String searchColumn = "";
        if(optSearch.equalsIgnoreCase("Mã NCC")) {
        	searchString = "'%" + searchTxt.trim() + "%'";
        	searchColumn = "sup_id";
        }
        else if(optSearch.equalsIgnoreCase("Tên NCC")) {
        	searchString = "'%" + searchTxt.trim() + "%'";
        	searchColumn = "sup_name";
        }
        else if(optSearch.equalsIgnoreCase("Số điện thoại")) {
        	searchString = "'%" + searchTxt.trim() + "%'";
        	searchColumn = "supp_phone";
        }
        String query = "select * from supplier where " + searchColumn +" like " + searchString;
        if(optSort.equalsIgnoreCase("Mã NCC giảm dần")) {
        	query = query + " order by sup_id desc";
        }
        else if(optSort.equalsIgnoreCase("Tên NCC")){
        	query = query + " order by sup_name asc";
        }
        System.out.println(query);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
            	Supplier supplier = new Supplier();
                supplier.setId(resultSet.getInt("sup_id"));
                supplier.setName(resultSet.getString("sup_name"));
                supplier.setAddress(resultSet.getString("sup_address"));
                supplier.setPhone(resultSet.getString("supp_phone"));
                searchList.add(supplier);
            }
        } catch (SQLException e) {
            System.out.println("Error while searching data");
        }        
        return searchList;
    }
}
