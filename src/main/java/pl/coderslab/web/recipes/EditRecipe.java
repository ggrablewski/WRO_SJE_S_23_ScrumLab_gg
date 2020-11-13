package pl.coderslab.web.recipes;

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

@WebServlet("/app/recipe/edit")
@Slf4j
public class EditRecipe extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int recipeId = getParameterAsInt(req,"id", 1);
        Recipe currRecipe = RecipeDao.read(recipeId);
        req.setAttribute("recipe", currRecipe);
        getServletContext().getRequestDispatcher("/app/edit-recipe.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Recipe newRecipe = new Recipe(req.getParameter("name"),
                req.getParameter("ingredients"),
                req.getParameter("description"),
                getParameterAsInt(req, "preparation_time", 0),
                req.getParameter("preparation"),
                (int) session.getAttribute("adminId"));
        newRecipe.setId(getParameterAsInt(req, "id", 0));
        RecipeDao.update(newRecipe);

        log.info("Zmieniono przepis " + newRecipe.toString(1));
        resp.sendRedirect("/app/recipes");
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
