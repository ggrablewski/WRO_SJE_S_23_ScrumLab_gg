package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/recipe/add")
public class AddRecipesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/add-recipe.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String preparationTime = req.getParameter("preparation_time");
        int preparation_time = Integer.parseInt(preparationTime);
        String preparation = req.getParameter("preparation");
        String ingredients = req.getParameter("ingredients");
        String adminId = req.getParameter("admin_id");
        int admin_id = Integer.parseInt(adminId);

        Recipe newRecipe = new Recipe(name, ingredients, description, preparation_time, preparation,admin_id);
        RecipeDao newRecipeDao = new RecipeDao();
        newRecipe = newRecipeDao.create(newRecipe);
        getServletContext().getRequestDispatcher("/add-recipe.jsp").forward(req, resp);
    }
}
