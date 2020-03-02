package filters;


import models.User;
import dao.UserDaoImpl;
import utils.MyUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebFilter(filterName = "cookieFilter", urlPatterns = {"/*"})
public class CookieFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = servletRequest.getSession();
        User userInSession = MyUtils.getLoginedUser(session);
        if (userInSession != null) {
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        Connection connection = MyUtils.getStoredConnection(servletRequest);
        String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if (checked == null || connection != null) {
            String userLogin = MyUtils.getUserLoginInCookie(servletRequest);
            UserDaoImpl userDao = new UserDaoImpl();
            User user = userDao.getByLogin(userLogin);
            MyUtils.storeLoginedUser(session, user);
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        //NOP
    }
}







