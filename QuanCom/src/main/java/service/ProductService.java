package service;

import bus.ProductRepository;
import bus.StaffRepository;
import model.Category;
import model.Material;
import model.Product;
import model.Roles;
import model.Staff;
import model.User;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductService {
    public List<Product> getAllProduct(){
        ProductRepository repository = new ProductRepository();
        return repository.getAllProduct();
    }

    public boolean addProduct(String name, int amount, String unit, int price, int cateId, String image){
        ProductRepository repository = new ProductRepository();
        return repository.addProduct(name, amount, unit, price, cateId, image) >= 1;
    }

    public boolean deleteProduct(int id){
        ProductRepository repository = new ProductRepository();
        return  repository.deleteProduct(id) >=1 ;
    }

    public boolean modifyProduct(
            String name,
            int amount,
            String unit,
            int price,
            int cateId,
            int productId,
            String image
    ){
        ProductRepository repository = new ProductRepository();
        return repository.modifyProduct(name, amount, unit, price, cateId, productId, image) >= 1;
    }
    
    //search list
    public List<Product> getAllSearchResult(String searchTxt, String optSearch, String optSort, String optCate){
    	ProductRepository repository = new ProductRepository();
        return repository.searchByOption(searchTxt, optSearch, optSort, optCate);
    }
    
}