package service;

import bus.CategoryRepository;
import model.Category;

import java.util.List;

public class CategoryService {
    public List<Category> getAllCate(){
        CategoryRepository repository = new CategoryRepository();
        List<Category> list = repository.getAllCategory();
        return list;
    }

    public boolean addMutualCate(String name, String description){
        CategoryRepository repository = new CategoryRepository();
        return repository.addMutualCate(name,description) >= 1;
    }

    public boolean mutualCateDetele(int id){
        CategoryRepository repository = new CategoryRepository();
        return repository.deleteMutualCate(id) >= 1;
    }

    public boolean mutualCateModify(int id, String name, String description){
        CategoryRepository repository = new CategoryRepository();
        return repository.modifyMutualCate(id, name, description) >= 1;
    }
}
