package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConfig {
    private static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3308/quancom";
    private static String USER_NAME = "root";
    private static String PASSWORD = "1234";


    public static Connection getConnection(){
        Connection connection = null;
        try {
            // dki sử dụng driver cho cơ sở dữ liệu MYSQL
            Class.forName(DRIVER_NAME);
            // Mở kết nối tới CSDL thoe driver đã chỉ định
            connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
        }catch (Exception e){
            System.out.println("Lỗi kết nối CSDL! MySqlConfig");
//            e.printStackTrace();
        }
        return connection;
    }
}
