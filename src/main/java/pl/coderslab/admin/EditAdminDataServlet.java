package pl.coderslab.admin;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/admin/edit-data")
public class EditAdminDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int adminId = (int) request.getSession().getAttribute("adminId");
        Admin adminUpdate = AdminDao.read(adminId);
        adminUpdate.setFirstName(request.getParameter("name"));
        adminUpdate.setLastName(request.getParameter("surname"));
        adminUpdate.setEmail(request.getParameter("email"));
        AdminDao.update(adminUpdate);

        getServletContext().getRequestDispatcher("/app/admin-edit-data.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app/admin-edit-data.jsp").forward(request, response);
    }
}
