package pl.coderslab.web.index;

import pl.coderslab.dao.AboutContactDao;
import pl.coderslab.model.AboutContact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/about")
public class About extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AboutContact> itemList = AboutContactDao.readAbout();
        req.setAttribute("about", itemList);
        getServletContext().getRequestDispatcher("/about.jsp").forward(req, resp);
    }
}

