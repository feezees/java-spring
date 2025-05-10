package com.example.demo.dto;

public class CounterResponse {
    private final long value;
    private final String timestamp;

    public CounterResponse(long value) {
        this.value = value;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public long getValue() {
        return value;
    }

    public String getTimestamp() {
        return timestamp;
    }
} 