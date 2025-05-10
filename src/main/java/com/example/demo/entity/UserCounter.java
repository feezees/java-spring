package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usercounters")
public class UserCounter {
    @Id
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long counterValue;

    public UserCounter() {
    }

    public UserCounter(String username) {
        this.username = username;
        this.counterValue = 0L;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getCounterValue() {
        return counterValue;
    }

    public void setCounterValue(Long counterValue) {
        this.counterValue = counterValue;
    }
} 