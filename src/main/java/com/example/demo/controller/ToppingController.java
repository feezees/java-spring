package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.RequireCookie;
import com.example.demo.dto.ToppingUpdateRequest;
import com.example.demo.entity.Cookies;
import com.example.demo.entity.Topping;
import com.example.demo.repository.ToppingRepository;
import com.example.demo.service.CounterService;
import com.example.demo.service.ToppingService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/toppings")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3000", "http://localhost:5500",
        "http://127.0.0.1:5500" })
@RequireCookie
public class ToppingController {
    public Cookies cookieEntity = new Cookies();
    @Autowired
    public CounterService counterService;

    @Autowired
    private ToppingService toppingService;

    @Autowired
    public ToppingRepository toppingRepository;

    @GetMapping
    public ResponseEntity<List<Topping>> getToppings() {
        List<Topping> t = toppingRepository.findAll();

        return ResponseEntity.ok(t);
    }

    @RequireCookie
    @PostMapping("/checkout")
    public ResponseEntity<Boolean> setToppings(HttpServletRequest request,
            @RequestBody List<ToppingUpdateRequest> updates) {
        String cookieUser = cookieEntity.getCookiesUser(request);

        // балик типА
        Long userCounterValue = counterService.getCounterValue(cookieUser);

        // сумма всех топингов
        long totalToppingsPrice = 0;
        for (ToppingUpdateRequest update : updates) {
            Topping topping = toppingRepository.findById(update.getId()).orElse(null);
            if (topping != null) {
                totalToppingsPrice += topping.getPrice().multiply(new BigDecimal(update.getCount())).longValue();
            }
        }

        System.out.println(totalToppingsPrice);

        if (userCounterValue < totalToppingsPrice) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }

        // вычесть кол-во
        boolean updated = toppingService.setToppings(updates);

        if (updated) {
            counterService.setCounterValue(cookieUser, userCounterValue - totalToppingsPrice);
            return ResponseEntity.ok(updated);
        }

        return ResponseEntity.badRequest().body(false);
    }
}
