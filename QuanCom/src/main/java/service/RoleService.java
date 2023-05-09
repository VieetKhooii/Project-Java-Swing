package service;

import model.Roles;
import bus.RoleRepository;
import java.util.List;

public class RoleService {
    public List<Roles> getAllRoles(){
        RoleRepository roleRepository = new RoleRepository();
        List<Roles> list = roleRepository.getAllRole();
        return list;
    }

    public boolean addRole(String roleName, String description){
        RoleRepository repository = new RoleRepository();
        return repository.addRole(roleName,description) >= 1;
    }

    public boolean roleDetele(int id){
        RoleRepository repository = new RoleRepository();
        return repository.deleteRole(id) >= 1;
    }

    public boolean roleModify(int id, String roleName, String description){
        RoleRepository repository = new RoleRepository();
        return repository.modifyRole(id, roleName, description) >= 1;
    }

    //search list
    public List<Roles> getAllSearchResult(String searchTxt, String optSearch, String optSort){
    	RoleRepository roleRepository = new RoleRepository();
        return roleRepository.searchByOption(searchTxt, optSearch, optSort);
    }
}
