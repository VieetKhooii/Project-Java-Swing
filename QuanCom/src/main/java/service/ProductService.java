package service;

import bus.ProductRepository;
import model.Product;

import java.util.List;

public class ProductService {
    public List<Product> getAllProduct(){
        ProductRepository repository = new ProductRepository();
        return repository.getAllProduct();
    }

    public boolean addProduct(String name, int amount, String unit, int price, int cateId){
        ProductRepository repository = new ProductRepository();
        return repository.addProduct(name, amount, unit, price, cateId) >= 1;
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
            int productId
    ){
        ProductRepository repository = new ProductRepository();
        return repository.modifyProduct(name, amount, unit, price, cateId, productId) >= 1;
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
