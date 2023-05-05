package bus;

import config.MySqlConfig;
import model.Product;

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
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println("ProductRepository: Error while getting product in database");
        }
        return list;
    }

    public int addProduct(String name, int amount, String unit, int price, int cateId){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO products(product_name, soluong, donvitinh, gia, category_id)" +
                " values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2, amount);
            preparedStatement.setString(3,unit);
            preparedStatement.setInt(4,price);
            preparedStatement.setInt(5,cateId);
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
            int productId){
        int isSuccess=0;
        Connection connection = MySqlConfig.getConnection();
        String query = "update products set product_name = ?, soluong = ?" +
                ", donvitinh = ?, gia = ?, category_id = ? where product_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setInt(2,amount);
            statement.setString(3,unit);
            statement.setInt(4,price);
            statement.setInt(5,cateId);
            statement.setInt(6,productId);
            isSuccess = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ProductRepository: Error modify product "+e.getMessage());
        }
        return isSuccess;
    }
}
