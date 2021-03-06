package pl.coderslab.web.index;

import pl.coderslab.dao.AdminDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if(AdminDao.verifyLogin(email, password)){
            Integer adminId = AdminDao.getAdminId(email);
            HttpSession session = request.getSession();
            Boolean ifLogged = true;
            session.setAttribute("adminId", adminId);
            session.setAttribute("logged", ifLogged);
            response.sendRedirect("/app/dashboard");
        }else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
