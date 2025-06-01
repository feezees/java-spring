package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.annotation.RequireCookie;
import com.example.demo.entity.Cookies;
import com.example.demo.service.CounterService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        Long userCounterValue = counterService.getCounter(cookieUser);

        return userCounterValue;
    }

    @RequireCookie
    @PutMapping("/increment")
    public ResponseEntity<Long> incrementCounter(HttpServletRequest request) {
        String cookieUser = cookieEntity.getCookiesUser(request);
        Long newCounterValue = counterService.incrementCounter(cookieUser);
        if (newCounterValue == -1L) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(newCounterValue);
        }
        return ResponseEntity.ok(newCounterValue);
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