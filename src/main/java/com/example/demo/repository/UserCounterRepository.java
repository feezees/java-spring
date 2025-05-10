package com.example.demo.repository;

import com.example.demo.entity.UserCounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCounterRepository extends JpaRepository<UserCounter, String> {
} 