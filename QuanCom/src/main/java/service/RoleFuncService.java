package service;

import bus.RoleFuncRepo;
import model.Functions;

import java.util.List;

public class RoleFuncService {
    public List<Functions> funcOfRole(int id){
        RoleFuncRepo repo = new RoleFuncRepo();
        return repo.funcOfRole(id);
    }

    public boolean addFuncOfRole(int funcId, int roleId){
        RoleFuncRepo repo = new RoleFuncRepo();
        return repo.addFuncOfRole(funcId,roleId) >= 1;
    }

    public boolean deteleFuncRole(int roleId){
        RoleFuncRepo repo = new RoleFuncRepo();
        return repo.deleteAllFuncRole(roleId) >= 1;
    }

    public boolean modifyFuncOfRole(int funcId, int roleId){
        RoleFuncRepo repo = new RoleFuncRepo();
        return repo.modifyFuncOfRole(funcId,roleId) >= 1;
    }

    public boolean deleteSpecificFuncOfRole(int funcId, int roleId){
        RoleFuncRepo repo = new RoleFuncRepo();
        return repo.deleteSpecificFuncRole(funcId, roleId) >= 1;
    }
}
