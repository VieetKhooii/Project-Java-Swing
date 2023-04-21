package bus;

import config.MySqlConfig;
import model.Roles;
import model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffRepository {
    public List<Staff> getAllStaff(){
        List<Staff> list = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String query = "select * from staffs";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Staff staff = new Staff();
                staff.setId(resultSet.getInt("staff_id"));
                staff.setName(resultSet.getString("name"));
                staff.setAddress(resultSet.getString("address"));
                staff.setGender(resultSet.getString("gender"));
                staff.setBirthDate(resultSet.getDate("date_of_birth"));
                staff.setPhone(resultSet.getString("phonenumber"));
                list.add(staff);
            }
        } catch (SQLException e) {
            System.out.println("Error while getting Staff in database");
        }
        return list;
    }

    public int addStaff(String name, String address, String phone, Date birthDate, String gender){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO staffs(name, address, phonenumber, date_of_birth, gender) " +
                "values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,address);
            preparedStatement.setString(3,phone);
            preparedStatement.setDate(4,birthDate);
            preparedStatement.setString(5,gender);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while adding staff "+e.getMessage());
        }
        return isSuccess;
    }

    public int modifyStaff(int id, String name, String address, String phone, Date birthDate, String gender){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "update staffs set name = ?, address = ?, phonenumber = ?, date_of_birth = ?, gender = ?" +
                " where staff_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,address);
            preparedStatement.setString(3,phone);
            preparedStatement.setDate(4,birthDate);
            preparedStatement.setString(5,gender);
            preparedStatement.setInt(6,id);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while modifying staff "+e.getMessage());
        }
        return isSuccess;
    }

    public int deleteStaff(int id){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "delete from staffs s where s.staff_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while deleting staff");
        }
        return isSuccess;
    }
}
