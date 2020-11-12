package pl.coderslab.web.plans;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet ("/app/plan/edit")
public class EditPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int planId = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("plan", PlanDao.readPlan(planId));
        getServletContext().getRequestDispatcher("/app/edit-plan.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String planName = req.getParameter("planName");
        String planDescription = req.getParameter("planDescription");
        Plan plan = PlanDao.readPlan(Integer.parseInt(req.getParameter("planId")));
        plan.setName(planName);
        plan.setDescription(planDescription);
        PlanDao.update(plan);
        resp.sendRedirect("/app/dashboard");
    }
}
