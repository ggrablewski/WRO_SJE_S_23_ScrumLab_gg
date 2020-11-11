package pl.coderslab.web;

import lombok.extern.slf4j.Slf4j;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/app/plan/add")
public class AddPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app/add-plan.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String planName = req.getParameter("planName");
        String planDescription = req.getParameter("planDescription");
        Plan newPlan = new Plan();
        newPlan.setName(planName);
        newPlan.setDescription(planDescription);
        int adminId = (int) req.getSession().getAttribute("adminId");
        newPlan.setAdminId(adminId);
        newPlan = PlanDao.create(newPlan);

        log.info("Dodano plan " + newPlan);
        resp.sendRedirect("/app/plan/list");
    }
}
