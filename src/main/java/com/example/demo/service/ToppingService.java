package com.example.demo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Topping;
import com.example.demo.repository.ToppingRepository;
import com.example.demo.dto.ToppingUpdateRequest;

@Service
public class ToppingService {

    @Autowired
    private ToppingRepository toppingRepository;

    public boolean setToppings(List<ToppingUpdateRequest> updates) {
        boolean updated = true;
        List<Topping> toppingsToSave = new ArrayList<>();

        for (ToppingUpdateRequest update : updates) {
            Optional<Topping> existingTopping = toppingRepository.findById(update.getId());
            if (existingTopping.isPresent()) {
                Topping topping = existingTopping.get();
                if (topping.getQuantity() >= update.getCount()) {
                    if (updated == true) {
                        topping.setQuantity(topping.getQuantity() - update.getCount());
                        toppingsToSave.add(topping);
                    }
                } else {
                    updated = false;
                }
            }
        }

        if (!toppingsToSave.isEmpty()) {
            toppingRepository.saveAll(toppingsToSave);
        }
        return updated;
    }

    public long calculateTotalToppingsPrice(List<ToppingUpdateRequest> updates) {
        long totalToppingsPrice = 0;
        for (ToppingUpdateRequest update : updates) {
            Topping topping = toppingRepository.findById(update.getId()).orElse(null);
            if (topping != null) {
                totalToppingsPrice += topping.getPrice().multiply(new BigDecimal(update.getCount())).longValue();
            }
        }
        return totalToppingsPrice;
    }
}