package service;

import bus.ProductRepository;
import model.Product;

import java.util.List;

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

    public int totalProductSoldAmount(int productId){
        ProductRepository repository = new ProductRepository();
        return repository.totalProductSoldAmount(productId);
    }

    public int totalPriceOfASoldProduct(int productId){
        ProductRepository repository = new ProductRepository();
        return repository.totalPriceOfASoldProduct(productId);
    }
}
