package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.QueryResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

@Service
public class QueryService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<QueryResponse> sendGetRequest() {
        try {
            return restTemplate.getForEntity("https://tenders.guru/api/hu/tenders", QueryResponse.class);
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to fetch data from tenders.guru", e);
        }
    }
}