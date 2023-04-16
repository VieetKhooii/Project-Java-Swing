package service;

import bus.FunctionRepository;
import bus.RoleRepository;
import model.Functions;
import model.Roles;

import java.util.List;

public class FunctionService {
    public List<Functions> getAllFunctions(){
        FunctionRepository functionRepository = new FunctionRepository();
        List<Functions> list = functionRepository.getAllFunction();
        return list;
    }

    public boolean addFunction(String name, String description){
        FunctionRepository functionRepository = new FunctionRepository();
        return functionRepository.addFunction(name,description) >= 1;
    }

    public boolean functionDetele(int id){
        FunctionRepository functionRepository = new FunctionRepository();
        return functionRepository.deleteFunction(id) >= 1;
    }

    public boolean functionModify(int id, String name, String description){
        FunctionRepository functionRepository = new FunctionRepository();
        return functionRepository.modifyFunction(id, name, description) >= 1;
    }
}
