package pl.coderslab.web;

import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/plan-add")
public class AddPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app/addPlan.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String planName = req.getParameter("planName");
        String planDescription = req.getParameter("planDescription");
        Plan newPlan = new Plan();
        newPlan.setName(planName);
        newPlan.setDescription(planDescription);
        resp.sendRedirect("/app/plan/list");
    }
}
