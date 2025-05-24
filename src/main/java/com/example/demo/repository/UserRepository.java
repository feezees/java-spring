package com.example.demo.repository;

import com.example.demo.entity.User;

import jakarta.validation.constraints.NotNull;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    User findByUsername(String username);

    List<User> findAll();

} 