package filters;

import models.ROLE;
import models.User;
import utils.MyUtils;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "securityManagerFilter", urlPatterns = {"/managerTask/*"})
public class SecurityManagerFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        User loginedUser = MyUtils.getLoginedUser(request.getSession());
        if (loginedUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }


        ROLE userRole = loginedUser.getRole();
        if (userRole == ROLE.USER ||userRole == ROLE.ADMIN) {
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/accessDenied.jsp");
            dispatcher.forward(request, response);
        }

        chain.doFilter(request, response);

    }


    @Override
    public void destroy() {
        //NOP
    }
}
