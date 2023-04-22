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
    public boolean login(String userName,String password){
        UserRepository repository = new UserRepository();
        return repository.login(userName,password) >= 1;
    }

    public boolean addUser(String userName,
                           String email,
                           String password,
                           int roleId){
        UserRepository userRepository = new UserRepository();
        return userRepository.addUser(
                userName,
                email,
                password,
                roleId) >= 1;
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
            int roleId
    ){
        UserRepository userRepository = new UserRepository();
        return  userRepository.modifyUser(
                id,
                userName,
                email,
                password,
                roleId
        ) >= 1;
    }
}
