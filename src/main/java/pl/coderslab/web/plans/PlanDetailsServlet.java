package pl.coderslab.web.plans;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.PlanDetails;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet ("/app/plan/details")
public class PlanDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int planId =Integer.parseInt(req.getParameter("id"));
        Plan plan = PlanDao.readPlan(planId);
        req.setAttribute("plan", plan);
        List<PlanDetails> list = PlanDao.getPlan(planId);
        req.setAttribute("days", DayNameDao.findAll());
        req.setAttribute("meals", list);
        req.setAttribute("admin", AdminDao.read(planId));
        getServletContext().getRequestDispatcher("/app/plan-details.jsp").forward(req, resp);
    }
}
