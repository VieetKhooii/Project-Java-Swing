package service;

import model.Staff;
import bus.StaffRepository;

import java.sql.Date;
import java.util.List;

public class StaffService {
    public List<Staff> getAllStaff(){
        StaffRepository repository = new StaffRepository();
        return repository.getAllStaff();
    }

    public boolean addStaff(String name, String address, String phone, Date birthDate, String gender, String image){
        StaffRepository repository = new StaffRepository();
        return repository.addStaff(name, address, phone, birthDate, gender, image) >= 1;
    }

    public boolean modifyStaff(int id, String name, String address, String phone, Date birthDate, String gender, String image){
        StaffRepository repository = new StaffRepository();
        return repository.modifyStaff(id, name, address, phone, birthDate, gender, image) >= 1;
    }

    public boolean deleteStaff(int id){
        StaffRepository repository = new StaffRepository();
        return repository.deleteStaff(id) >= 1;
    }
    //search list
    public List<Staff> getAllSearchResult(String searchTxt, String optSearch, String optSort){
        StaffRepository repository = new StaffRepository();
        return repository.searchByOption(searchTxt, optSearch, optSort);
    }


}