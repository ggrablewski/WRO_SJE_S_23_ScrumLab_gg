package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/recipeDetails")
public class RecipeDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = getParameterAsInt(req, "id", 0);
        Recipe recipe = RecipeDao.read(id);
        req.setAttribute("recipe", recipe);
        getServletContext().getRequestDispatcher("/recipeDetails.jsp").forward(req, resp);
    }

    public int getParameterAsInt(HttpServletRequest req, String paramName, int dftValue) {
        int param = dftValue;
        try {
            param = Integer.parseInt(req.getParameter(paramName));
        } catch (NumberFormatException nfe) {
        }
        return param;
    }
}