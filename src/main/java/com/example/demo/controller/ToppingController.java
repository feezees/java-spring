package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.RequireCookie;
import com.example.demo.dto.ToppingUpdateRequest;
import com.example.demo.entity.Topping;
import com.example.demo.repository.ToppingRepository;
import com.example.demo.service.ToppingService;

@RestController
@RequestMapping("/api/toppings")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3000", "http://localhost:5500",
        "http://127.0.0.1:5500" })
@RequireCookie
public class ToppingController {

    @Autowired
    private ToppingService toppingService;

    @Autowired
    public ToppingRepository toppingRepository;

    @GetMapping
    public ResponseEntity<List<Topping>> getToppings() {
        List<Topping> t = toppingRepository.findAll();

        return ResponseEntity.ok(t);
    }

    @PostMapping("/quantity")
    public ResponseEntity<Boolean> setToppings(@RequestBody List<ToppingUpdateRequest> updates) {
        boolean updated = toppingService.setToppings(updates);
        return ResponseEntity.ok(updated);
    }
}
