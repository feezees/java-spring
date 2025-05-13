package com.example.demo.entity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import jakarta.servlet.http.Cookie;

public class Cookies {
    private static String getCookieByName(Cookie[] cookies, String name) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public String getCookiesUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String userCookie = cookies != null ? getCookieByName(cookies, "user") : null;

        if (userCookie == null || userCookie.isEmpty()) {
            return "undefined";
        }

        return userCookie;
    }

    public void removeCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("user", null); // Значение не важно
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void setCookieUser(HttpServletResponse response,  String value) {
        Cookie cookie = new Cookie("user", value);
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        cookie.setDomain(null);
        cookie.setHttpOnly(false);
        cookie.setSecure(false);
        response.addCookie(cookie);
    }
}
