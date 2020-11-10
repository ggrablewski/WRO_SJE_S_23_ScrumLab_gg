package pl.coderslab.web;

import lombok.extern.slf4j.Slf4j;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname  = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Admin newAdmin = new Admin(name, surname, email, password);
        newAdmin.setEnable(0);
        newAdmin.setSuperadmin(0);

        AdminDao newAdminDao = new AdminDao();
        newAdmin = newAdminDao.create(newAdmin);
//        TODO zmienić w xml ścieżkę zapisu logów (później se o tym poczytam)
        log.info("Dodano użytkownika %s", newAdmin);

        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
