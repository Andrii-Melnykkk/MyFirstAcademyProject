package services;

import dao.UserDaoImpl;
import models.ROLE;
import models.User;
import utils.MyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class UserServices {
    private static final String REG_LINK = "http://localhost:8080";

    private String errorMessage;
    private boolean hasError;
    private boolean remember;

    public boolean hasError() {
        return hasError;
    }


    public boolean isRemembered() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void registerUserService(HttpServletRequest req) {

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);


        if (name == null || name.length() == 0) {
            errorMessage = "Name input is empty!";
        }
        if (login == null || login.length() == 0) {
            errorMessage = "Login input is empty!";
        }
        if (email == null || email.length() == 0) {
            errorMessage = "Email input is empty!";
        }
        if (password == null || password.length() == 0) {
            errorMessage = "Password should be greater than 6 and less than 20 characters!";
        }
        UserDaoImpl userDao = new UserDaoImpl();
        for (User users : userDao.getAll()) {
            if (users.getLogin().equals(login)) {
                errorMessage = "User with such login exists!";
                break;
            }
        }
        for (User users : userDao.getAll()) {
            if (users.getEmail().equals(email)) {
                errorMessage = "User with such email exists!";
                break;
            }
        }

        if (errorMessage == null) {
            userDao.add(user);
            MailSendService mailSendService = new MailSendService();
            mailSendService.sendEmail(user.getEmail(),
                    "Registration",
                    "You have successfully registered. \n" +
                            REG_LINK);
        }

        req.setAttribute("errorMessage", errorMessage);
        req.setAttribute("user", user);
    }

    public void validateUserService(HttpServletRequest req, HttpServletResponse resp) {
        String userLogin = req.getParameter("login");
        String userPassword = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe");
        remember = "Y".equals(rememberMe);

        User user = null;

        if (userLogin == null || userPassword == null || userLogin.length() == 0 || userPassword.length() == 0) {
            hasError = true;
            errorMessage = "Required User login and password!";
        } else {
            user = new UserDaoImpl().getByLoginAndPassword(userLogin, userPassword);
            if (user == null) {
                hasError = true;
                errorMessage = "User with such login or password does not exist!";
            }
        }
        if (hasError) {
            user = new User();
            user.setLogin(userLogin);
            user.setPassword(userPassword);

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("user", user);
        } else {
            HttpSession session = req.getSession();
            MyUtils.storeLoginedUser(session, user);
            if (remember) {
                MyUtils.storeUserCookie(resp, user);
            } else {
                MyUtils.deleteUserCookie(resp);
            }
        }

    }

    public void getAllUsersWithoutAdminsService(HttpServletRequest req) {

        List<User> usersWithoutAdmins = new UserDaoImpl().getAllUsersWithoutAdmins();

        if (usersWithoutAdmins == null) {
            errorMessage = "Sorry, user list is empty.";
        }

        req.setAttribute("errorMessage", errorMessage);
        req.setAttribute("usersWithoutAdmins", usersWithoutAdmins);
    }

    public void changeUsersRole(HttpServletRequest req) {
        long userID = Long.parseLong(req.getParameter("id"));
        String role = req.getParameter("role");
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.get(userID);

        switch (role) {
            case "User":
                user.setRole(ROLE.USER);
                break;
            case "Manager":
                user.setRole(ROLE.MANAGER);
                break;
            case "Administrator":
                user.setRole(ROLE.ADMIN);
                break;
            default:
                break;
        }
        userDao.update(user);
    }

}
