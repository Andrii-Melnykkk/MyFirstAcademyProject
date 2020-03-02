package controller;

import org.apache.log4j.Logger;
import services.AdvertisementServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/advertisementListApproved"})
public class AdvertisementListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(AdvertisementListController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        AdvertisementServices advertisementServices = new AdvertisementServices();
        advertisementServices.getApprovedAdvertisementListService(req);

        RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/view/advertisementListApproved.jsp");
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
