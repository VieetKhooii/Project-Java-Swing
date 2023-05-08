package service;

import model.Material;
import model.Product;
import model.Recipe;

import java.util.List;

public class FoodCalculation {
    public static void productAmountCal(List<Product> productList,
                                        List<Recipe> recipeList,
                                        List<Material> materialList,
                                        ProductService productService,
                                        boolean database){
        int min;
        int temp=0;
        for (Product product : productList){
            min=-1;
            temp=0;
            for (Recipe recipe : recipeList){
                if (product.getId() == recipe.getProductId()){
                    for (Material material : materialList){
                        if (material.getId() == recipe.getMaterialId()){
                            temp = material.getAmount()/recipe.getAmount();
                            break;
                        }
                    }
                    if (min==-1) min = temp;
                    else if (temp < min) min = temp;
                }
            }
            if (min==-1){
                product.setAmount(0);
            }
            else {
                product.setAmount(min);
            }

            if (database){
                productService.modifyProduct(product.getName(),product.getAmount(),product.getUnit(),
                        product.getPrice(),product.getCategoryId(),product.getId());
            }

        }
    }

    public static void materialConsumption(int id,
                                           List<Product> cart,
                                           List<Product> productList,
                                           List<Recipe> recipeList,
                                           List<Material> materialList,
                                           DetailOrderService detailOrderService,
                                           MaterialService materialService){
        for (Product product : cart){
            int materialCostAmount=0;
            boolean checkIfTrue = false;
            detailOrderService.addDetailOrder(id,product.getId(),product.getName(),product.getAmount()
                    ,product.getPrice());
            for (Product product1 : productList){
                if (product1.getId() == product.getId()){
                    for (Recipe recipe : recipeList){
                        if (recipe.getProductId() == product1.getId()){
                            materialCostAmount = recipe.getAmount() * product.getAmount();
                            for (Material material : materialList){
                                if (material.getId() == recipe.getMaterialId()){
                                    materialService.modifyMaterial(
                                            material.getName(),
                                            material.getUnit(),
                                            material.getPrice(),
                                            material.getAmount()-materialCostAmount,material.getId()
                                    );
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void calculateSameRecipeFood(int amount,
                                               int id,
                                               List<Product> productList,
                                               List<Recipe> recipeList,
                                               List<Material> materialList,
                                               ProductService productService){
        int consumption=0;
        for (Product product : productList){
            if (product.getId() == id){
                for (Recipe recipe : recipeList){
                    if (recipe.getProductId() == id){
                        consumption = recipe.getAmount()*amount;
                        for (Material material : materialList){
                            if (material.getId() == recipe.getMaterialId()){
                                material.setAmount(material.getAmount()-consumption);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
            productAmountCal(productList,recipeList,materialList,productService,false);
        }
    }
}
