package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void baz() {
        // List<String> ls = List.of("A", "B", "C");
        // Post xd = new Post(52L, ls);
        // List<String> ww = xd.getPostData();
        // System.out.println(ww);

        UUID xd = UUID.randomUUID();
        System.out.println(xd);

    }

    public static void bar() {
        int[] arr = { 1, 2, 3, 4, 5 };
        // filter even numbers
        int[] evenNumbers = Arrays.stream(arr)
                .filter(n -> n % 2 == 0)
                .toArray();

        System.out.println(Arrays.toString(evenNumbers));
    }

    public static <T> void logCounterResponse(T value) {
        try {
            String jsonString = new ObjectMapper().writeValueAsString(value);
            System.out.println("Response: " + jsonString);
        } catch (Exception e) {
            System.err.println("Error logging response: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        System.out.println("################# success #################");
        // baz();
    }
}