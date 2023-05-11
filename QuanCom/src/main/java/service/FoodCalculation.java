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
                        product.getPrice(),product.getCategoryId(),product.getId(), product.getImage());
            }

        }
    }

    public static void materialConsumption(int orderId,
                                           List<Product> cart,
                                           List<Product> productList,
                                           List<Recipe> recipeList,
                                           List<Material> materialList,
                                           DetailOrderService detailOrderService,
                                           MaterialService materialService){
        for (Product product : cart){
            detailOrderService.addDetailOrder(orderId,product.getId(),product.getName(),product.getAmount()
                    ,product.getPrice());
            for (Product product1 : productList){
                if (product1.getId() == product.getId()){
                    for (Recipe recipe : recipeList){
                        if (recipe.getProductId() == product1.getId()){
                            for (Material material : materialList){
                                if (material.getId() == recipe.getMaterialId()){
                                    materialService.modifyMaterial(
                                            material.getName(),
                                            material.getUnit(),
                                            material.getPrice(),
                                            material.getAmount(),
                                            material.getId()
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
                                               int productId,
                                               List<Product> productList,
                                               List<Recipe> recipeList,
                                               List<Material> materialList,
                                               ProductService productService){
        if (amount != 0){
            int consumption=0;
            for (Product product : productList){
                if (product.getId() == productId){
                    for (Recipe recipe : recipeList){
                        if (recipe.getProductId() == productId){
                            consumption = recipe.getAmount()*amount;
                            for (Material material : materialList){
                                if (material.getId() == recipe.getMaterialId()){
//                                    System.out.println("hi");
                                    material.setAmount(material.getAmount()-consumption);
//                                    System.out.println("after consump: "+material.getAmount());
                                    break;
                                }
                            }
                        }
                    }
                }
                productAmountCal(productList,recipeList,materialList,productService,false);
            }
        }
    }

    public static void returnMaterial(int amount, int productId, List<Material> materialList,
                                      List<Product> productList, List<Recipe> recipeList,ProductService productService){
        for (Product product : productList){
            if (product.getId() == productId){
                for (Recipe recipe : recipeList){
                    if (recipe.getProductId() == productId){
                        int returnAmount=0;
                        returnAmount = recipe.getAmount()*amount;
                        for (Material material : materialList){
                            if (material.getId() == recipe.getMaterialId()){
//                                System.out.println(returnAmount);
//                                System.out.println("get amount old: "+material.getAmount());
                                material.setAmount(material.getAmount()+returnAmount);
//                                System.out.println("get amount: "+material.getAmount());
                                break;
                            }
                        }
                    }
                }
                productAmountCal(productList,recipeList,materialList,productService,false);
            }
        }
    }
}
