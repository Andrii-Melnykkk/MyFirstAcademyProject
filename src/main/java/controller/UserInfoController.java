package controller;

import models.User;
import org.apache.log4j.Logger;
import utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/userInfo"})
public class UserInfoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(UserInfoController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loginedUser = MyUtils.getLoginedUser(session);
        if (loginedUser == null) {
            try {
                resp.sendRedirect(req.getContextPath() + "/login");
            } catch (IOException e) {
                LOGGER.error("IO exception occurred", e);

            }
            return;
        }
        req.setAttribute("user", loginedUser);
        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/userInfo.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            LOGGER.error("Servlet exception occurred", e);
        } catch (IOException e) {
            LOGGER.error("IO exception occurred", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doGet(req, resp);
        } catch (ServletException e) {
            LOGGER.error("Servlet exception occurred", e);
        } catch (IOException e) {
            LOGGER.error("IO exception occurred", e);
        }
    }
}
