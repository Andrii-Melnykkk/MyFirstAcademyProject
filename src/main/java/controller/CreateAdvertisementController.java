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

@WebServlet(urlPatterns = {"/createAdvertisement"})
public class CreateAdvertisementController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(CreateAdvertisementController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/view/createAdvertisement.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            LOGGER.error("Servlet exception occurred", e);
        }
        catch (IOException e) {
            LOGGER.error("IO exception occurred", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AdvertisementServices advertisementServices = new AdvertisementServices();
        advertisementServices.createAdvertisementService(req);
        if (advertisementServices.getErrorMessage() != null) {
            RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/view/createAdvertisement.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException e) {
                LOGGER.error("Servlet exception occurred", e);
            } catch (IOException e) {
                LOGGER.error("IO exception occurred", e);
            }
        } else {
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/awaitPage.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                LOGGER.error("Servlet exception occurred", e);
            } catch (IOException e) {
                LOGGER.error("IO exception occurred", e);
            }
        }
    }
}
