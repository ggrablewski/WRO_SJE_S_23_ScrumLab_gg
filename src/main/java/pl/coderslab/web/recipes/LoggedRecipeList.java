package pl.coderslab.web.recipes;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet ("/app/recipes")
public class LoggedRecipeList  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int adminId =(int) session.getAttribute("adminId");
        System.out.println(adminId);
        List<Recipe> recipeList = RecipeDao.findAllForUser(adminId);
        req.setAttribute("recipeList", recipeList);
        getServletContext().getRequestDispatcher("/app/recipes.jsp").forward(req, resp);
    }
}
