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
                staff.setImage(resultSet.getString("image"));
                list.add(staff);
            }
        } catch (SQLException e) {
            System.out.println("Error while getting Staff in database");
        }
        return list;
    }

    public int addStaff(String name, String address, String phone, Date birthDate, String gender, String image){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO staffs(name, address, phonenumber, date_of_birth, gender, image) " +
                "values (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,address);
            preparedStatement.setString(3,phone);
            preparedStatement.setDate(4,birthDate);
            preparedStatement.setString(5,gender);
            preparedStatement.setString(6,image);
            isSuccess = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while adding staff "+e.getMessage());
        }
        return isSuccess;
    }

    public int modifyStaff(int id, String name, String address, String phone, Date birthDate, String gender, String image){
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "update staffs set name = ?, address = ?, phonenumber = ?, date_of_birth = ?, gender = ?, image = ?" +
                " where staff_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,address);
            preparedStatement.setString(3,phone);
            preparedStatement.setDate(4,birthDate);
            preparedStatement.setString(5,gender);
            preparedStatement.setString(6,image);
            preparedStatement.setInt(7,id);
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
    
    //Search by option
    public List<Staff> searchByOption(String searchTxt, String optSearch, String optSort){
        List<Staff> searchList = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String searchString="";
        String searchColumn = "";
        if(optSearch.equalsIgnoreCase("Mã nhân viên")) {
        	searchString = "'%" + searchTxt.trim() + "%'";
        	searchColumn = "staff_id";
        }
        else if(optSearch.equalsIgnoreCase("Tên nhân viên")) {
        	searchString = "'%" + searchTxt.trim() + "%'";
        	searchColumn = "name";
        }
        else if(optSearch.equalsIgnoreCase("Số điện thoại")) {
        	searchString = "'%" + searchTxt.trim() + "%'";
        	searchColumn = "name";
        }
        String query = "select * from staffs where " + searchColumn +" like " + searchString;
        if(optSort.equalsIgnoreCase("Mã nhân viên giảm dần")) {
        	query = query + " order by staff_id desc";
        }
        else if(optSort.equalsIgnoreCase("Tên nhân viên")){
        	query = query + " order by name asc";
        }
        System.out.println(query);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
            	Staff staff = new Staff();
                staff.setId(resultSet.getInt("staff_id"));
                staff.setName(resultSet.getString("name"));
                staff.setAddress(resultSet.getString("address"));
                staff.setGender(resultSet.getString("gender"));
                staff.setBirthDate(resultSet.getDate("date_of_birth"));
                staff.setPhone(resultSet.getString("phonenumber"));
                staff.setImage(resultSet.getString("image"));
                searchList.add(staff);
            }
        } catch (SQLException e) {
            System.out.println("Error while searching data");
        }        
        return searchList;
    }
}
