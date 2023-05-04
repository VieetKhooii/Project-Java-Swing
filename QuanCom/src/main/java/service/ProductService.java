package service;

import bus.ProductRepository;
import model.Category;
import model.Material;
import model.Product;
import model.Roles;
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
    
    
    //Search by name
    public List<Product> searchByName(String name, List<Product> productList){
    	List<Product> searchList = new ArrayList<>();  
    	for(Product i : productList) {
    		Pattern pattern = Pattern.compile("(?i).*"+Normalizer.normalize(name, Normalizer.Form.NFD)
            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")+".*");
    		String vietnameseString = Normalizer.normalize(i.getName(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
    		String search = vietnameseString.toLowerCase().replaceAll("[đ]", "d")                          
                    .replaceAll("[ư]", "u")                                      
                    .replaceAll("[ô]", "o")
                    .replaceAll("[ơ]", "o")
                    .replaceAll("[ê]", "e")
                    .replaceAll("[ă]", "a")
                    .replaceAll("[â]", "a");                
            Matcher matcher = pattern.matcher(search);
            if(matcher.find()) {
            	searchList.add(i);
        	}           
    	}
    	return searchList;
    }
    
    //Search by id
    public List<Product> searchById(String id, List<Product> productList){
    	List<Product> searchList = new ArrayList<>();  
    	for(Product i : productList) {
    		Pattern pattern = Pattern.compile(".*"+id+".*");              
            Matcher matcher = pattern.matcher(String.valueOf(i.getId()));
            if(matcher.find()) {
            	searchList.add(i);
        	}           
    	}
    	return searchList;
    }
    //Search by category
    public List<Product> searchByCategory(String category, List<Product> productList){
    	List<Product> searchList = new ArrayList<>(); 
    	CategoryService categoryService = new CategoryService();
    	List<Category> categoryList = categoryService.getAllCate();
    	for(Product i : productList) {
    		for(Category j : categoryList) {
    			if(i.getCategoryId() == j.getId() && category.equalsIgnoreCase(j.getName())) {   				
    				searchList.add(i);
    	            break;
    			}
    		}   		     
    	}
    	return searchList;
    }
    //Search by quality
    public List<Product> searchByQuality(String quality, List<Product> productList){
    	List<Product> searchList = new ArrayList<>();  
    	for(Product i : productList) {
    		Pattern pattern = Pattern.compile(".*"+quality+".*");              
            Matcher matcher = pattern.matcher(String.valueOf(i.getAmount()));
            if(matcher.find()) {
            	searchList.add(i);
        	}           
    	}
    	return searchList;
    }
    //Sort by id
    public List<Product> sortById(List<Product> productList){
    	List<Product> sortList = productList;  
    	Collections.sort(sortList, new Comparator<Product>() {

			@Override
			public int compare(Product o1, Product o2) {
				// TODO Auto-generated method stub
				return o2.getId() - o1.getId();
			}
    		
    	});
    	return sortList;
    }
    //Sort by name
    public List<Product> sortByName(List<Product> productList){
    	List<Product> sortList = productList;  
    	Collections.sort(sortList, new Comparator<Product>() {

			@Override
			public int compare(Product o1, Product o2) {
				// TODO Auto-generated method stub
				return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
			}
    		
    	});
    	return sortList;
    }
    //Sort by quality asc
    public List<Product> sortByQualityAsc(List<Product> productList){
    	List<Product> sortList = productList;  
    	Collections.sort(sortList, new Comparator<Product>() {

			@Override
			public int compare(Product o1, Product o2) {
				// TODO Auto-generated method stub
				return o1.getAmount() - o2.getAmount();
			}
    		
    	});
    	return sortList;
    }
    //Sort by quality des
    public List<Product> sortByQualityDes(List<Product> productList){
    	List<Product> sortList = productList;  
    	Collections.sort(sortList, new Comparator<Product>() {

			@Override
			public int compare(Product o1, Product o2) {
				// TODO Auto-generated method stub
				return o2.getAmount() - o1.getAmount();
			}
    		
    	});
    	return sortList;
    }
    //Sort by price asc
    public List<Product>  sortByPriceAsc(List<Product> productList){
    	List<Product> sortList = productList;  
    	Collections.sort(sortList, new Comparator<Product>() {

			@Override
			public int compare(Product o1, Product o2) {
				// TODO Auto-generated method stub
				return o1.getPrice() - o2.getPrice();
			}
    		
    	});
    	return sortList;
    }
    //Sort by price des
    public List<Product>  sortByPriceDes(List<Product> productList){
    	List<Product> sortList = productList;  
    	Collections.sort(sortList, new Comparator<Product>() {

			@Override
			public int compare(Product o1, Product o2) {
				// TODO Auto-generated method stub
				return o2.getPrice() - o1.getPrice();
			}
    		
    	});
    	return sortList;
    }
}