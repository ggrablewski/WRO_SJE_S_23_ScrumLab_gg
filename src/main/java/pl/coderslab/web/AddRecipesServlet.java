package pl.coderslab.web;

import lombok.extern.slf4j.Slf4j;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebServlet("/recipe-add")
public class AddRecipesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/app/add-recipe.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String preparationTime = req.getParameter("preparation_time");
        int preparation_time = Integer.parseInt(preparationTime);
        String preparation = req.getParameter("preparation");
        String ingredients = req.getParameter("ingredients");
        HttpSession session = req.getSession();
        int admin_id = (int) session.getAttribute("adminId");

        Recipe newRecipe = new Recipe(name, ingredients, description, preparation_time, preparation,admin_id);
        newRecipe = RecipeDao.create(newRecipe);

        log.info("Dodano Przepis " + newRecipe);
        getServletContext().getRequestDispatcher("/recipes.jsp").forward(req, resp);
    }
}
