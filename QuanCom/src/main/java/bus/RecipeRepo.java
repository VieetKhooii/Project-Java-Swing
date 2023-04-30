package bus;

import config.MySqlConfig;
import model.Recipe;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeRepo {
    public List<Recipe> getAllRecipe(){
        List<Recipe> list = new ArrayList<>();
        Connection connection = MySqlConfig.getConnection();
        String query = "select * from chitietcongthuc";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Recipe recipe = new Recipe();
                recipe.setProductId(resultSet.getInt("product_id"));
                recipe.setMaterialId(resultSet.getInt("material_id"));
                recipe.setAmount(resultSet.getInt("soluong"));
                list.add(recipe);
            }
        } catch (SQLException e) {
            System.out.println("RecipeRepo: Error while getting recipe in database");
        }
        return list;
    }
}
