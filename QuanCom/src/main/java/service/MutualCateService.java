package service;

import bus.MutualCategoryRepository;
import bus.RoleRepository;
import model.MutualCategory;
import model.Roles;

import java.util.List;

public class MutualCateService {
    public List<MutualCategory> getAllMutualCate(){
        MutualCategoryRepository repository = new MutualCategoryRepository();
        List<MutualCategory> list = repository.getAllMutualCategory();
        return list;
    }

    public boolean addMutualCate(String name, String description){
        MutualCategoryRepository repository = new MutualCategoryRepository();
        return repository.addMutualCate(name,description) >= 1;
    }

    public boolean mutualCateDetele(int id){
        MutualCategoryRepository repository = new MutualCategoryRepository();
        return repository.deleteMutualCate(id) >= 1;
    }

    public boolean mutualCateModify(int id, String name, String description){
        MutualCategoryRepository repository = new MutualCategoryRepository();
        return repository.modifyMutualCate(id, name, description) >= 1;
    }
}
