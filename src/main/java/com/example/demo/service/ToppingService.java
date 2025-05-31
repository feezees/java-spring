package com.example.demo.service;

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
        boolean updated = false;
        List<Topping> toppingsToSave = new ArrayList<>();

        for (ToppingUpdateRequest update : updates) {
            Optional<Topping> existingTopping = toppingRepository.findById(update.getId());
            if (existingTopping.isPresent()) {
                Topping topping = existingTopping.get();
                if (topping.getQuantity() >= update.getCount()) {
                    topping.setQuantity(update.getCount());
                    toppingsToSave.add(topping);
                    updated = true;
                }
            }
        }
        if (!toppingsToSave.isEmpty()) {
            toppingRepository.saveAll(toppingsToSave);
        }
        return updated;
    }
} 