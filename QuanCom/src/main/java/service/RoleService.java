package service;

import model.Roles;
import bus.RoleRepository;

import java.util.List;

public class RoleService {
    public List<Roles> getAllUsers(){
        RoleRepository roleRepository = new RoleRepository();
        List<Roles> list = roleRepository.getAllRole();
        return list;
    }
}
