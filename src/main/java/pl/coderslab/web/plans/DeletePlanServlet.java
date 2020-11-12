package pl.coderslab.web.plans;

import pl.coderslab.dao.PlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet ("/app/plan/delete")
public class DeletePlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int planId = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("id", planId);
        getServletContext().getRequestDispatcher("/app/confirm-plan-delete.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int planId = Integer.parseInt(req.getParameter("planId"));
        System.out.println(planId);
        PlanDao.delete(planId);
        resp.sendRedirect("/app/plan/list");
    }
}
