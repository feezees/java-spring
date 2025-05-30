package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.annotation.RequireCookie;
import com.example.demo.entity.Cookies;
import com.example.demo.service.CounterService;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/counter")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3000", "http://localhost:5500",
        "http://127.0.0.1:5500" })
public class CounterController {

    private final CounterService counterService;
    public Cookies cookieEntity = new Cookies();

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping
    @RequireCookie
    public Long getIncrementValue(HttpServletRequest req) {
        String cookieUser = cookieEntity.getCookiesUser(req);
        Long userCounterValue = counterService.incrementCounter(cookieUser);

        return userCounterValue;
    }

    @PostMapping
    public Long incrementCounter(@RequestBody String username) {
        Long newValue = counterService.incrementCounter(username);
        DemoApplication.logCounterResponse(new CounterResponse(username, newValue));
        return newValue;
    }
}

class CounterResponse {
    private final String username;
    private final Long value;

    public CounterResponse(String username, Long value) {
        this.username = username;
        this.value = value;
    }

    public String getUsername() {
        return username;
    }

    public Long getValue() {
        return value;
    }
}