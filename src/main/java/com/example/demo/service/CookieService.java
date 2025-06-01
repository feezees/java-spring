package com.example.demo.service;

import com.example.demo.entity.Cookies;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieService {

    private final UserRepository userRepository;
    private final Cookies cookies;

    @Autowired
    public CookieService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.cookies = new Cookies(); // Cookies class no longer needs UserRepository in its constructor for this purpose
    }

    public User getUserByCookie(HttpServletRequest request) {
        String username = cookies.getCookiesUser(request);
        if (username == null || username.isEmpty() || "undefined".equals(username)) {
            return null;
        }
        return userRepository.findByUsername(username);
    }
} 