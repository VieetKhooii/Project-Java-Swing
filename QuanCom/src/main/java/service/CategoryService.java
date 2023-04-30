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

    public boolean addCate(String name, String description){
        CategoryRepository repository = new CategoryRepository();
        return repository.addCate(name,description) >= 1;
    }

    public boolean cateDelete(int id){
        CategoryRepository repository = new CategoryRepository();
        return repository.deleteCate(id) >= 1;
    }

    public boolean cateModify(int id, String name, String description){
        CategoryRepository repository = new CategoryRepository();
        return repository.modifyCate(id, name, description) >= 1;
    }
}
