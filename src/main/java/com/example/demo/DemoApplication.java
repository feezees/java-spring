package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Topping;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.math.BigDecimal;

@SpringBootApplication
public class DemoApplication {
    public static <T> void logCounterResponse(T value) {
        try {
            String jsonString = new ObjectMapper().writeValueAsString(value);
            System.out.println("Response: " + jsonString);
        } catch (Exception e) {
            System.err.println("Error logging response: " + e.getMessage());
        }
    }

    private static void readAndPrintDataJson() {
        Path filePath = Path.of("src/main/resources/json/data.json");
        try {
            String content = Files.readString(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            // Assuming the JSON is an array of objects like [{"name": "...", ...}, {"name":
            // "...", ...}]
            java.util.List<java.util.Map<String, Object>> data = objectMapper.readValue(content,
                    new com.fasterxml.jackson.core.type.TypeReference<java.util.List<java.util.Map<String, Object>>>() {
                    });

            System.out.println("Names from data.json:");
            for (java.util.Map<String, Object> item : data) {
                if (item.containsKey("name")) {
                    System.out.println(item.get("name"));
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading or processing data.json: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        System.out.println("################# success #################");

        readAndPrintDataJson();

    }

}