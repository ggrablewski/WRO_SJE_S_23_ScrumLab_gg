package pl.coderslab.web.recipes;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebServlet ("/app/recipe/plan/add")
public class AddRecipeToPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int adminId= (int) session.getAttribute("adminId");
        req.setAttribute("plans", PlanDao.findAllForAdmin(adminId));
        req.setAttribute("days", DayNameDao.findAll());
        req.setAttribute("recipes", RecipeDao.findAll());
        getServletContext().getRequestDispatcher("/app/add-recipe-to-plan.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String planName = req.getParameter("plan");
        String day = req.getParameter("day");
        String recipeName = req.getParameter("recipe");
        String mealName = req.getParameter("mealName");
        int mealNum = Integer.parseInt(req.getParameter("displayNum"));
        int recipeId = RecipeDao.readByName(recipeName).getId();
        int planId = PlanDao.readByName(planName).getId();
        int dayId = DayNameDao.readByName(day).getId();
        RecipePlan recipePlan = new RecipePlan(recipeId, mealName, mealNum, dayId, planId);
        RecipePlanDao.addRecipePlan(recipePlan);
        resp.sendRedirect("/app/recipe/plan/add");
    }
}
