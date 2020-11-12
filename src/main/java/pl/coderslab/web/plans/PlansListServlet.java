package pl.coderslab.web.plans;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/plan/list")
public class PlansListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int adminId = (int) request.getSession().getAttribute("adminId");
        List<Plan> adminPlans = PlanDao.findAllForAdmin(adminId);
        request.setAttribute("adminPlans", adminPlans);
        getServletContext().getRequestDispatcher("/app/plan-list.jsp").forward(request,response);
    }
}