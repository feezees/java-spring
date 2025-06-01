package com.example.demo.service;

import com.example.demo.entity.UserCounter;
import com.example.demo.repository.UserCounterRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CounterService {
    private final UserCounterRepository counterRepository;
    private final UserRepository userRepository;

    public CounterService(UserCounterRepository counterRepository, UserRepository userRepository) {
        this.counterRepository = counterRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Long setCounterValue(String username, Long counerValue) {
        if (!userRepository.existsByUsername(username)) {
            return -1L; // User not found
        }

        UserCounter counter = counterRepository.findById(username)
                .orElse(new UserCounter(username));

        counter.setCounterValue(counerValue);

        return counter.getCounterValue();
    }

    public Long getCounterValue(String username) {
        if (!userRepository.existsByUsername(username)) {
            return -1L; // User not found
        }

        UserCounter counter = counterRepository.findById(username)
                .orElse(new UserCounter(username));

        return counter.getCounterValue();
    }

    public Long getCounter(String username){
        if (!userRepository.existsByUsername(username)) {
            return -1L; // User not found
        }

        UserCounter counter = counterRepository.findById(username)
                .orElse(new UserCounter(username));


        return counter.getCounterValue();
    }

    @Transactional
    public Long incrementCounter(String username) {
        if (!userRepository.existsByUsername(username)) {
            return -1L; // User not found
        }

        UserCounter counter = counterRepository.findById(username)
                .orElse(new UserCounter(username));

        counter.setCounterValue(counter.getCounterValue() + 1);
        counterRepository.save(counter);

        return counter.getCounterValue();
    }
}