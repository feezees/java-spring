package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    
    public String getGreeting(String name) {
        return String.format("Welcome to Spring Boot, %s!", name);
    }
} 