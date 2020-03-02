package services;

import dao.AdvertisementDaoImpl;
import dao.UserDaoImpl;
import models.Advertisement;
import models.TYPE;
import models.User;
import utils.MyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdvertisementServices {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void createAdvertisementService(HttpServletRequest req) {
        String content = req.getParameter("content");
        String text = req.getParameter("text");
        String type = req.getParameter("type");
        HttpSession session = req.getSession();
        User loginedUser = MyUtils.getLoginedUser(session);


        if (content == null || content.length() == 0) {
            errorMessage = "Please fill in the content field!";
        }
        if (text == null || text.length() == 0) {
            errorMessage = "Your Advertisement is empty!";
        }
        if (loginedUser == null) {
            errorMessage = "Please login before posting advertisement!";
        }

        Advertisement advertisement = new Advertisement();

        if (errorMessage == null) {
            String userLogin = loginedUser.getLogin();
            UserDaoImpl userDao = new UserDaoImpl();
            User user = userDao.getByLogin(userLogin);
            advertisement.setContent(content);
            advertisement.setText(text);
            switch (type) {
                case "Other":
                    advertisement.setType(TYPE.OTHER);
                    break;
                case "Buying ads":
                    advertisement.setType(TYPE.BUYING_ADS);
                    break;
                case "Sales ads":
                    advertisement.setType(TYPE.SALES_ADS);
                    break;
                default:
                    break;
            }
            advertisement.setUser(user);
            AdvertisementDaoImpl advertisementDao = new AdvertisementDaoImpl();
            advertisementDao.add(advertisement);
        }

        req.setAttribute("errorMessage", errorMessage);
        req.setAttribute("advertisement", advertisement);
    }

    public void getApprovedAdvertisementListService(HttpServletRequest req) {

        AdvertisementDaoImpl advertisementDao = new AdvertisementDaoImpl();
        List<Advertisement> advertisementListApproved = advertisementDao.getAllApproved();

        if (advertisementListApproved == null) {
            errorMessage = "Sorry, advertisement list is empty";
        }


        req.setAttribute("errorMessage", errorMessage);
        req.setAttribute("advertisementListApproved", advertisementListApproved);
    }

    public void getAdvertisementListToApproveService(HttpServletRequest req) {

        AdvertisementDaoImpl advertisementDao = new AdvertisementDaoImpl();
        List<Advertisement> advertisementListToApprove = advertisementDao.getAllAdvToApprove();

        if (advertisementListToApprove == null) {
            errorMessage = "Sorry, advertisement list is empty";
        }

        req.setAttribute("errorMessage", errorMessage);
        req.setAttribute("advertisementListToApprove", advertisementListToApprove);
    }

    public void approveOrRejectAdvertisementService(HttpServletRequest req) {




        long advID = Long.parseLong(req.getParameter("id"));
        String approveAdv = req.getParameter("Approve");
        String rejectAdv = req.getParameter("Reject");
        AdvertisementDaoImpl advertisementDao = new AdvertisementDaoImpl();
        Advertisement adv = advertisementDao.get(advID);
        String userEmail = adv.getUser().getEmail();
        MailSendService mailSendService = new MailSendService();
        String subject = "Your advertisement status.";
        String text;
        if (approveAdv != null) {
            adv.setApproved(true);
            text = "Your advertisement was approved by manager. Now u can see it on bulletin board!";
            mailSendService.sendEmail(userEmail, subject, text);
        }
        if (rejectAdv != null) {
            adv.setApproved(false);
            text = "Your advertisement was rejected by manager. Please read FAQ page before posting advertisement.";
            mailSendService.sendEmail(userEmail, subject, text);
        }
        advertisementDao.setApproved(adv);
    }

}

