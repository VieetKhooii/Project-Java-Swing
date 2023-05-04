package service;

import bus.RecipeRepo;
import model.Recipe;
import model.Roles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecipeService {
    public List<Recipe> getAllRecipe(){
        RecipeRepo repo = new RecipeRepo();
        List<Recipe> list = repo.getAllRecipe();
        return list;
    }

    public boolean addRecipe(int productId, int materialId, int amount){
        RecipeRepo repo = new RecipeRepo();
        return repo.addRecipe(productId, materialId, amount) >= 1;
    }

    public boolean deleteRecipe(int productId, int materialId){
        RecipeRepo repo = new RecipeRepo();
        return repo.deleteRecipe(productId, materialId) >= 1;
    }

    public boolean modifyRecipe(int productId, int materialId, int amount, int oldProduct, int oldMaterial){
        RecipeRepo repo = new RecipeRepo();
        return repo.modifyRecipe(productId, materialId, amount, oldProduct, oldMaterial) >= 1;
    }
    
    //Search by productId
    public List<Recipe> searchByProductId(String productId, List<Recipe> recipeList){
    	List<Recipe> searchList = new ArrayList<>();  
    	for(Recipe i : recipeList) {
    		Pattern pattern = Pattern.compile(".*"+productId+".*");              
            Matcher matcher = pattern.matcher(String.valueOf(i.getProductId()));
            
            if(matcher.find()) {
            	searchList.add(i);
        	}           
    	}
    	return searchList;
    }
    //Search by materialId
    public List<Recipe> searchByMaterialId(String materialId, List<Recipe> recipeList){
    	List<Recipe> searchList = new ArrayList<>();  
    	for(Recipe i : recipeList) {
    		Pattern pattern = Pattern.compile(".*"+materialId+".*");              
            Matcher matcher = pattern.matcher(String.valueOf(i.getMaterialId()));
            
            if(matcher.find()) {
            	searchList.add(i);
        	}           
    	}
    	return searchList;
    }
    //Sort by productId asc
    public List<Recipe> sortByProductIdAsc(List<Recipe> recipeList){
    	List<Recipe> sortList = recipeList;  
    	Collections.sort(sortList, new Comparator<Recipe>() {

			@Override
			public int compare(Recipe o1, Recipe o2) {
				// TODO Auto-generated method stub
				return o1.getProductId() - o2.getProductId();
			}
    		
    	});
    	return sortList;
    }
    // sort by productId des
    public List<Recipe> sortByProductIdDes(List<Recipe> recipeList){
    	List<Recipe> sortList = recipeList;  
    	Collections.sort(sortList, new Comparator<Recipe>() {

			@Override
			public int compare(Recipe o1, Recipe o2) {
				// TODO Auto-generated method stub
				return o2.getProductId() - o1.getProductId();
			}
    		
    	});
    	return sortList;
    }
    //Sort by material Id asc
    public List<Recipe> sortByMaterialIdAsc(List<Recipe> recipeList){
    	List<Recipe> sortList = recipeList;  
    	Collections.sort(sortList, new Comparator<Recipe>() {

			@Override
			public int compare(Recipe o1, Recipe o2) {
				// TODO Auto-generated method stub
				return o1.getMaterialId() - o2.getMaterialId();
			}
    		
    	});
    	return sortList;
    }
    //Sort by material id des
    public List<Recipe> sortByMaterialIdDes(List<Recipe> recipeList){
    	List<Recipe> sortList = recipeList;  
    	Collections.sort(sortList, new Comparator<Recipe>() {

			@Override
			public int compare(Recipe o1, Recipe o2) {
				// TODO Auto-generated method stub
				return o2.getMaterialId() - o1.getMaterialId();
			}
    		
    	});
    	return sortList;
    }
}