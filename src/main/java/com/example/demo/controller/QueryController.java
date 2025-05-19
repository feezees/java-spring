package com.example.demo.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.demo.service.QueryService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3000", "http://localhost:5500",
        "http://127.0.0.1:5500" })
public class QueryController {
    
    @Autowired
    private QueryService queryService;

    @GetMapping("/tenders")
    public ResponseEntity<List<String>> getTenders() {
        ResponseEntity<String> dataResponse = queryService.sendGetRequest();
        String responseBody = dataResponse.getBody();
        
        ObjectMapper mapper = new ObjectMapper();
        List<String> ids = new ArrayList<>();

        try {
            JsonNode root = mapper.readTree(responseBody);
            JsonNode dataNode = root.get("data");

            if (dataNode != null && dataNode.isArray()) {
                for (JsonNode item : dataNode) {
                    // Get the "id" field from each object in the data array
                    JsonNode idNode = item.get("id");
                    if (idNode != null && idNode.isTextual()) {
                        ids.add(idNode.asText());
                    }
                }
            }
            return ResponseEntity.ok(ids);

        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
