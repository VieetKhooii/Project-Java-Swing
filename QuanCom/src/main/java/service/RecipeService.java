package service;

import bus.RecipeRepo;
import model.Recipe;
import java.util.List;

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

    //search list
    public List<Recipe> getAllSearchResult(String searchTxt, String optSearch, String optSort){
        RecipeRepo repo = new RecipeRepo();
        return repo.searchByOption(searchTxt, optSearch, optSort);
    }
}