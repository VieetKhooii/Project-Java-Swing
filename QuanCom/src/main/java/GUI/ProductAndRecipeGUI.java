package GUI;

import javax.swing.*;
import java.awt.*;

public class ProductAndRecipeGUI extends JTabbedPane{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    static ProductGUI product;
    static RecipeGUI recipe;
    static CategoryGUI category;
    public ProductAndRecipeGUI() {
        // TODO Auto-generated constructor stub
        init();
    }

    private void init() {
        setSize(new Dimension(1080, 700));

        //tab món ăn
        product = new ProductGUI();
        product.setPreferredSize(new Dimension(1080, 700));
        this.add("Món ăn", product);
        //tab công thức
        recipe = new RecipeGUI();
        recipe.setPreferredSize(new Dimension(1080, 700));
        this.add("Công thức", recipe);
        //tab phân loại
        category = new CategoryGUI();
        category.setPreferredSize(new Dimension(1080, 700));
        this.add("Phân loại", category);
    }


}