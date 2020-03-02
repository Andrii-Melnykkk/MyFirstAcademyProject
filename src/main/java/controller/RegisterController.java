package controller;

import org.apache.log4j.Logger;
import services.UserServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(RegisterController.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/view/register.jsp");
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

        UserServices userServices = new UserServices();
        userServices.registerUserService(req);
        if (userServices.getErrorMessage() != null) {
            RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/view/register.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException e) {
                LOGGER.error("Servlet exception occurred", e);
            } catch (IOException e) {
                LOGGER.error("IO exception occurred", e);
            }
        } else {
            try {
                resp.sendRedirect(req.getContextPath() + "/login");
            } catch (IOException e) {
                LOGGER.error("IO exception occurred", e);
            }
        }
    }
}
