package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.LastPlanDetails;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {

    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan(name,description,created,admin_id) VALUES (?,?, NOW(),?);";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan WHERE id = ?;";
    private static final String FIND_ALL_PLANS_QUERY = "SELECT * FROM plan;";
    private static final String FIND_ALL_PLANS_FOR_USER_QUERY = "SELECT * FROM plan WHERE admin_id=?;";
    private static final String COUNT_ALL_PLANS_FOR_USER_QUERY = "SELECT COUNT(*) FROM plan WHERE admin_id=?;";
    private static final String READ_PLAN_QUERY = "SELECT * FROM plan WHERE id = ?;";
    private static final String READ_LAST_PLAN_NAME_QUERY = "SELECT name FROM plan WHERE id = (SELECT MAX(id) from plan WHERE admin_id = ?);";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ? WHERE id = ?;";
    private static final String SELECT_LAST_PLAN_QUERY =
            "SELECT day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.description as recipe_description " +
                    "FROM `recipe_plan` " +
                    "JOIN day_name on day_name.id=day_name_id " +
                    "JOIN recipe on recipe.id=recipe_id WHERE " +
                    "recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?) " +
                    "ORDER by day_name.display_order, recipe_plan.display_order";


    public static Plan readPlan(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                    plan.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;

    }


    public static List<Plan> findAll() {
        List<Plan> plansList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Plan plan = new Plan();
                plan.setId(resultSet.getInt("id"));
                plan.setName(resultSet.getString("name"));
                plan.setDescription(resultSet.getString("description"));
                plan.setCreated(resultSet.getString("created"));
                plan.setAdminId(resultSet.getInt("admin_id"));
                plansList.add(plan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plansList;

    }

    public static List<Plan> findAllForAdmin(Integer adminId) {
        List<Plan> plansList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_FOR_USER_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Plan plan = new Plan();
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                    plan.setAdminId(resultSet.getInt("admin_id"));
                    plansList.add(plan);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plansList;

    }


    public static Plan create(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, plan.getName());
            insertStm.setString(2, plan.getDescription());
            insertStm.setInt(3, plan.getAdminId());
            int result = insertStm.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_QUERY)) {
            statement.setInt(1, planId);
            statement.executeUpdate();
            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
            statement.setInt(3, plan.getId());
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int countPlans(int id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_ALL_PLANS_FOR_USER_QUERY)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public static List<LastPlanDetails> getLastPlan(int adminId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_LAST_PLAN_QUERY);
            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();
            List<LastPlanDetails> lastPlan = new ArrayList<>();
            while (resultSet.next()) {
                LastPlanDetails planDetails = new LastPlanDetails();
                planDetails.setDayName(resultSet.getString("day_name"));
                planDetails.setMealName(resultSet.getString("meal_name"));
                planDetails.setRecipeName(resultSet.getString("recipe_name"));
                planDetails.setRecipeDesc(resultSet.getString("recipe_description"));
                lastPlan.add(planDetails);
            }
            return lastPlan;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static String lastPlanName(int adminId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_LAST_PLAN_NAME_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


}
