package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cookies;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/cookie")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3000", "http://localhost:5500",
        "http://127.0.0.1:5500" }, allowCredentials = "true")
public class CookieController {

    public boolean successValue = true;

    public Cookies cookieEntity = new Cookies();

    @PutMapping("/admin")
    public String setAdminCookie(HttpServletRequest request, HttpServletResponse response) {
        cookieEntity.setCookieUser(response, "admin");
        return "admin cookie added";
    }

    @DeleteMapping
    public String deleteCookie(HttpServletResponse response) {
        cookieEntity.removeCookie(response);
        return "cookie removed";
    }

    @GetMapping("/check")
    public ResponseEntity<String> checkCookie(HttpServletRequest request) {
        String cookieUser = cookieEntity.getCookiesUser(request);

        if (cookieUser == "undefined"){
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user cookie not found");
        }

        return ResponseEntity.ok().body(cookieUser);
    }
}