package service;

import bus.RoleFuncRepo;
import model.Functions;

import java.util.List;

public class RoleFuncService {
    public List<Functions> funcOfRole(int id){
        RoleFuncRepo repo = new RoleFuncRepo();
        return repo.funcOfRole(id);
    }
}
