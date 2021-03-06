package pl.coderslab.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;
import pl.coderslab.web.recipes.EditRecipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {

private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(name,ingredients,description,created,updated," +
        "preparation_time,preparation,admin_id) VALUES (?,?,?,NOW(),NOW(),?,?,?);";
private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?;";
private static final String FIND_ALL_RECIPES_QUERY = "SELECT * FROM recipe;";
private static final String FIND_ALL_RECIPES_FOR_USER_QUERY = "SELECT * FROM recipe WHERE admin_id=?;";
private static final String COUNT_ALL_RECIPES_FOR_USER_QUERY = "SELECT COUNT(*) FROM recipe WHERE admin_id=?;";
private static final String READ_RECIPE_QUERY = "SELECT * from recipe where id = ?;";
private static final String READ_RECIPE_BY_NAME_QUERY = "SELECT * from recipe where name = ?;";
private static final String UPDATE_RECIPE_QUERY = "UPDATE recipe SET name = ?, ingredients = ?, description = ?, " +
        "updated = NOW(), preparation_time = ?, preparation = ?, admin_id = ? WHERE id = ?;";

    public static Recipe read(Integer recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY)
        ) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getString("created"));
                    recipe.setUpdated(resultSet.getString("updated"));
                    recipe.setPreparation_time(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdmin_id(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }

    public static List<Recipe> findAll() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getString("created"));
                recipeToAdd.setUpdated(resultSet.getString("updated"));
                recipeToAdd.setPreparation_time(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipeToAdd.setAdmin_id(resultSet.getInt("admin_id"));
                recipeList.add(recipeToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;

    }

    public static Recipe create(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, recipe.getName());
            insertStm.setString(2,recipe.getIngredients());
            insertStm.setString(3, recipe.getDescription());
            insertStm.setInt(4, recipe.getPreparation_time());
            insertStm.setString(5, recipe.getPreparation());
            insertStm.setInt(6, recipe.getAdmin_id());
            int result = insertStm.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setId(generatedKeys.getInt(1));
                    return recipe;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(Integer recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_QUERY)) {
            statement.setInt(1, recipeId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Recipe not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final Logger log = LoggerFactory.getLogger(EditRecipe.class);

    public static void update(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_QUERY)) {
            statement.setInt(7, recipe.getId());
            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getIngredients());
            statement.setString(3, recipe.getDescription());
            statement.setInt(4, recipe.getPreparation_time());
            statement.setString(5, recipe.getPreparation());
            statement.setInt(6, recipe.getAdmin_id());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int countRecipes(int id){
        try(Connection connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(COUNT_ALL_RECIPES_FOR_USER_QUERY)){
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()){
                resultSet.next();
                return resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public static Recipe readByName(String recipeName) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_BY_NAME_QUERY)
        ) {
            statement.setString(1, recipeName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getString("created"));
                    recipe.setUpdated(resultSet.getString("updated"));
                    recipe.setPreparation_time(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdmin_id(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }

    public static List<Recipe> findAllForUser(int adminId) {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPES_FOR_USER_QUERY);
             ) {
            statement.setInt(1, adminId);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Recipe recipeToAdd = new Recipe();
                    recipeToAdd.setId(resultSet.getInt("id"));
                    recipeToAdd.setName(resultSet.getString("name"));
                    recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                    recipeToAdd.setDescription(resultSet.getString("description"));
                    recipeToAdd.setCreated(resultSet.getString("created"));
                    recipeToAdd.setUpdated(resultSet.getString("updated"));
                    recipeToAdd.setPreparation_time(resultSet.getInt("preparation_time"));
                    recipeToAdd.setPreparation(resultSet.getString("preparation"));
                    recipeToAdd.setAdmin_id(resultSet.getInt("admin_id"));
                    recipeList.add(recipeToAdd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }
}
