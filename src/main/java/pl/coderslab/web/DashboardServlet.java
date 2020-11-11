package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.LastPlanDetails;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet ("/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int adminId =(int) session.getAttribute("adminId");
        req.setAttribute("planCount", PlanDao.countPlans(adminId));
        req.setAttribute("recipeCount", RecipeDao.countRecipes(adminId));
        List<LastPlanDetails> list = PlanDao.getLastPlan(adminId);
        req.setAttribute("planName", PlanDao.lastPlanName(adminId));
        req.setAttribute("days", DayNameDao.findAll());
        req.setAttribute("meals", list);
        req.setAttribute("admin", AdminDao.read(adminId));
        getServletContext().getRequestDispatcher("/app/dashboard.jsp").forward(req,resp);
    }
}
