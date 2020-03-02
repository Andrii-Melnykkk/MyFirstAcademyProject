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


@WebServlet(urlPatterns = {"/managerTask"})
public class ManagerTaskController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ManagerTaskController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AdvertisementServices advertisementServices = new AdvertisementServices();
        advertisementServices.getAdvertisementListToApproveService(req);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/managerTask.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            LOGGER.error("Servlet exception occurred", e);
        } catch (IOException e) {
            LOGGER.error("IO exception occurred", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AdvertisementServices advertisementServices = new AdvertisementServices();
        advertisementServices.approveOrRejectAdvertisementService(req);
        try {
            doGet(req, resp);
        } catch (ServletException e) {
            LOGGER.error("Servlet exception occurred", e);
        } catch (IOException e) {
            LOGGER.error("IO exception occurred", e);
        }
    }
}
