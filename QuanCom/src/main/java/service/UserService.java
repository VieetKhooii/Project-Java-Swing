package service;

import model.Roles;
import model.Staff;
import model.User;
import bus.StaffRepository;
import bus.UserRepository;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {

    public List<User> getAllUsers(){
        UserRepository userRepository = new UserRepository();
        List<User> list = userRepository.getAllUser();
        return list;
    }
    public boolean login(String userName,String password){
        UserRepository repository = new UserRepository();
        return repository.login(userName,password) >= 1;
    }

    public boolean addUser(String userName,
                           String email,
                           String password,
                           int roleId,
                           int staffId){
        UserRepository userRepository = new UserRepository();
        return userRepository.addUser(
                userName,
                email,
                password,
                roleId,
                staffId) >= 1;
    }

    public boolean deleteUser(int id){
        UserRepository userRepository = new UserRepository();
        return userRepository.deleteUser(id) >= 1;
    }

    public boolean modifyUser(
            int id,
            String userName,
            String email,
            String password,
            int roleId,
            int staffId
    ){
        UserRepository userRepository = new UserRepository();
        return  userRepository.modifyUser(
                id,
                userName,
                email,
                password,
                roleId,
                staffId
        ) >= 1;
    }

    //search list
    public List<User> getAllSearchResult(String searchTxt, String optSearch, String optSort){
        UserRepository userRepository = new UserRepository();
        return userRepository.searchByOption(searchTxt, optSearch, optSort);
    }
}