package utils;

import java.sql.Connection;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;

public class MyUtils {
    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";

    private static final String ATT_NAME_USER_LOGIN = "ATTRIBUTE_FOR_STORE_USER_LOGIN_IN_COOKIE";

    private MyUtils() {
    }

    //store connection in request attribute
    public static void storeConnection(ServletRequest request, Connection connection) {
        request.setAttribute(ATT_NAME_CONNECTION, connection);
    }

    // get the connection object has been stored in attribute of the request.
    public static Connection getStoredConnection(ServletRequest request) {
        return (Connection) request.getAttribute(ATT_NAME_CONNECTION);
    }

    // Store user info in Session.
    public static void storeLoginedUser(HttpSession session, User loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }

    // Get the user information stored in the session.
    public static User getLoginedUser(HttpSession session) {
        return (User) session.getAttribute("loginedUser");
    }

    // Store user login in Cookie
    public static void storeUserCookie(HttpServletResponse response, User user) {
        Cookie cookieUserLogin = new Cookie(ATT_NAME_USER_LOGIN, user.getLogin());
        cookieUserLogin.setHttpOnly(true);
        cookieUserLogin.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserLogin);
    }

    public static String getUserLoginInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_LOGIN.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    // Delete cookie.
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_LOGIN, null);
        cookieUserName.setHttpOnly(true);
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }

}

