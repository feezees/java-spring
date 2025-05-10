package com.example.demo.controller;

import com.example.demo.DemoApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/log")
public class LogController {

    @PostMapping
    public int logObject(@RequestBody Object object) {
        DemoApplication.logCounterResponse(object);
        return 52; 
    }
} 