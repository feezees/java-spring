package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.RequireCookie;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3000", "http://localhost:5500",
        "http://127.0.0.1:5500" })
@RequireCookie
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<String>> getAllUsernames() {
        List<String> usernames = userRepository.findAll().stream()
            .map(User::getUsername)
            .collect(Collectors.toList());
        return ResponseEntity.ok(usernames);
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username){
        User user = userRepository.findByUsername(username);
        System.out.println(user);
        return user;
    }
} 