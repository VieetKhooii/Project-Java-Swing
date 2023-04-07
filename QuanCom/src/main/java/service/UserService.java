package service;

import model.User;
import bus.UserRepository;

import java.util.List;

public class UserService {

    public List<User> getAllUsers(){
        UserRepository userRepository = new UserRepository();
        List<User> list = userRepository.getAllUser();
        return list;
    }
    public boolean login(String email,String password){
        UserRepository repository = new UserRepository();
        return repository.login(email,password) >= 1;
    }

    public boolean addUser(String userName,
                           String fullName,
                           String email,
                           String password,
                           String address,
                           String phone,
                           int roleId){
        UserRepository userRepository = new UserRepository();
        return userRepository.addUser(
                userName,
                fullName,
                email,
                password,
                address,
                phone,
                roleId) >= 1;
    }

    public boolean deleteUser(int id){
        UserRepository userRepository = new UserRepository();
        return userRepository.deleteUser(id) >= 1;
    }
}