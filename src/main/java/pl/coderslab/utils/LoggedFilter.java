package pl.coderslab.utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/app/*")
public class LoggedFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        try {
            Boolean isLogged = session != null && (Boolean) session.getAttribute("logged");
            if (isLogged) {
                chain.doFilter(req, resp);
            } else {
                ((HttpServletResponse) resp).sendRedirect("/login");
            }
        } catch (NullPointerException exception) {
            exception.printStackTrace();
            ((HttpServletResponse) resp).sendRedirect("/login");
        }

    }
}