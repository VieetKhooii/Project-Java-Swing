package service;

import bus.CategoryRepository;
import model.Category;
import model.Roles;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    
    
    //Search by id
    public List<Category> searchById(String id, List<Category> categoryList){
    	List<Category> searchList = new ArrayList<>();  
    	for(Category i : categoryList) {
    		Pattern pattern = Pattern.compile(".*"+id+".*");              
            Matcher matcher = pattern.matcher(String.valueOf(i.getId()));
            
            if(matcher.find()) {
            	searchList.add(i);
        	}           
    	}
    	return searchList;
    }
    //Search by name
    public List<Category> searchByName(String name, List<Category> categoryList){
    	List<Category> searchList = new ArrayList<>();  
    	for(Category i : categoryList) {
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
            if(matcher.find() || name.equals(i.getName())) {
            	searchList.add(i);
        	}           
    	}
    	return searchList;
    }
    //Sort by id
    public List<Category> sortById(List<Category> categoryList){
    	List<Category> sortList = categoryList;  
    	Collections.sort(sortList, new Comparator<Category>() {

			@Override
			public int compare(Category o1, Category o2) {
				// TODO Auto-generated method stub
				return o2.getId() - o1.getId();
			}
    		
    	});
    	return sortList;
    }
    //sort by name
    public List<Category> sortByName(List<Category> categoryList){
    	List<Category> sortList = categoryList;  
    	Collections.sort(sortList, new Comparator<Category>() {

			@Override
			public int compare(Category o1, Category o2) {
				// TODO Auto-generated method stub
				return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
			}
    		
    	});
    	return sortList;
    }
}