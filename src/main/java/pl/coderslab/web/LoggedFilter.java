package pl.coderslab.web;

import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/app/*")
public class LoggedFilter implements Filter {

    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, java.io.IOException {
        HttpSession session = ((HttpServletRequest) req).getSession(false);
        Boolean isLogged = session != null && (Boolean) session.getAttribute("ifLogged");
        if (isLogged) {
            chain.doFilter(req, resp);
        } else {
            ((HttpServletResponse) resp).sendRedirect("/login.jsp");
        }
    }
}