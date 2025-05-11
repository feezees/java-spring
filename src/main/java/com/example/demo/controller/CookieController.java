package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/cookie")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3000", "http://localhost:5500",
        "http://127.0.0.1:5500" }, allowCredentials = "true")
public class CookieController {

    public boolean successValue = true;

    @PutMapping("/admin")
    public String setAdminCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) {
            System.out.println("cookie not found");
        }

        Cookie cookie = new Cookie("user", "Admin");
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        cookie.setDomain(null);
        cookie.setHttpOnly(false);
        cookie.setSecure(false);
        response.addCookie(cookie);

        return "admin cookie added";
    }

    @DeleteMapping
    public String deleteCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("user", null); // Значение не важно
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "cookie removed";
    }

    @GetMapping("/check")
    public String checkCookie(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) {
            return "cookie not found";
        }

        StringBuilder result = new StringBuilder("Список кук:<br>");
        for (Cookie cookie : cookies) {
            result.append(String.format(
                    "Имя: %s, Значение: %s, Путь: %s, MaxAge: %d<br>",
                    cookie.getName(),
                    cookie.getValue(),
                    cookie.getPath(),
                    cookie.getMaxAge()));
        }

        System.out.println(result.toString());
        return result.toString();
    }

}