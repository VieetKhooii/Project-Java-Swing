package bus;

import config.MySqlConfig;
import model.Product;
import model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public List<Product> getAllProduct(){
        List<Product> list = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String query = "select * from products";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("product_name"));
                product.setAmount(resultSet.getInt("soluong"));
                product.setUnit(resultSet.getString("donvitinh"));
                product.setPrice(resultSet.getInt("gia"));
                product.setCategoryId(resultSet.getInt("category_id"));
                product.setImage(resultSet.getString("image"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println("ProductRepository: Error while getting product in database");
        }
        return list;
    }

    public int addProduct(String name, int amount, String unit, int price, int cateId, String image){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO products(product_name, soluong, donvitinh, gia, category_id, image)" +
                " values (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2, amount);
            preparedStatement.setString(3,unit);
            preparedStatement.setInt(4,price);
            preparedStatement.setInt(5,cateId);
            preparedStatement.setString(6,image);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ProductRepository: Error while add product "+e.getMessage());
        }
        return isSuccess;
    }

    public int deleteProduct(int id){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "delete from products p where p.product_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ProductRepository: Error deleting product "+e.getMessage());
        }
        return isSuccess;
    }

    public int modifyProduct(
            String name,
            int amount,
            String unit,
            int price,
            int cateId,
            int productId,
            String image){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "update products set product_name = ?, soluong = ?" +
                ", donvitinh = ?, gia = ?, category_id = ?, image = ? where product_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setInt(2,amount);
            statement.setString(3,unit);
            statement.setInt(4,price);
            statement.setInt(5,cateId);
            statement.setString(6,image);
            statement.setInt(7,productId);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ProductRepository: Error modify product "+e.getMessage());
        }
        return isSuccess;
    }
    
    //Search by option
    public List<Product> searchByOption(String searchTxt, String optSearch, String optSort, String optCate){
        List<Product> searchList = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String searchString="";
        String searchColumn = "";
        if(optSearch.equalsIgnoreCase("Mã")) {
        	searchString = "'%" + searchTxt.trim() + "%'";
        	searchColumn = "product_id";
        }
        else if(optSearch.equalsIgnoreCase("Tên")) {
        	searchString = "'%" + searchTxt.trim() + "%'";
        	searchColumn = "product_name";
        }
        else if(optSearch.equalsIgnoreCase("Số lượng")) {
        	searchString = "'%" + searchTxt.trim() + "%'";
        	searchColumn = "soluong";
        }
        String query = "select p.product_id, p.product_name, p.soluong, p.donvitinh, p.gia,p.category_id, c.cate_name, p.image from products as p join category as c on p.category_id=c.cate_id and " 
        + searchColumn +" like " + searchString;
        if(!optCate.equalsIgnoreCase("Tất cả")){
        	query = query + " and c.cate_name='" + optCate.trim() + "'";
        }
        if(optSort.equalsIgnoreCase("Mã giảm dần")) {
        	query = query + " order by product_id desc";
        }
        else if(optSort.equalsIgnoreCase("Tên")){
        	query = query + " order by product_name asc";
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
            	Product product = new Product();
                product.setId(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("product_name"));
                product.setAmount(resultSet.getInt("soluong"));
                product.setUnit(resultSet.getString("donvitinh"));
                product.setPrice(resultSet.getInt("gia"));
                product.setCategoryId(resultSet.getInt("category_id"));
                product.setImage(resultSet.getString("image"));
                searchList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error while searching data");
        }        
        return searchList;
    }
    public int totalProductSoldAmount(int productId){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "SELECT COALESCE(SUM(ct.soluong), 0) as total_amount\n" +
                "FROM products p \n" +
                "LEFT JOIN chitiet_orders ct ON p.product_id = ct.product_id\n" +
                "WHERE p.product_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                isSuccess = resultSet.getInt("total_amount");
            }
        } catch (SQLException e) {
            System.out.println("ProductRepository: Error while getting total sold amount of a product");
        }
        return isSuccess;
    }

    public int totalPriceOfASoldProduct(int productId){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "SELECT COALESCE(SUM(ct.gia), 0) as total_price\n" +
                "FROM products p \n" +
                "LEFT JOIN chitiet_orders ct ON p.product_id = ct.product_id\n" +
                "WHERE p.product_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                isSuccess = resultSet.getInt("total_price");
            }
        } catch (SQLException e) {
            System.out.println("ProductRepository: Error while getting total sold price of a product");
        }
        return isSuccess;
    }
}