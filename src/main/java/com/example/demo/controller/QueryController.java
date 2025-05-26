package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.RequireCookie;
import com.example.demo.dto.QueryResponse;
import com.example.demo.model.FailedResponseBody;
import com.example.demo.model.QueryResponseBodyItem;
import com.example.demo.service.QueryService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3000", "http://localhost:5500",
        "http://127.0.0.1:5500" })
@RequireCookie
public class QueryController {
    private FailedResponseBody failedResponseBody = new FailedResponseBody(HttpStatus.UNAUTHORIZED);

    @Autowired
    private QueryService queryService;

    @GetMapping("/tenders")
    public ResponseEntity<Object> getTenders(HttpServletRequest req) {
        try {
            ResponseEntity<QueryResponse> dataResponse = queryService.sendGetRequest();
            QueryResponseBodyItem[] ResponseBody = dataResponse.getBody().data;
            
            List<String> ids = Arrays.stream(ResponseBody)
                .map(item -> item.id)
                .collect(Collectors.toList());

            return ResponseEntity.ok(ids);
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(failedResponseBody);
        }
    }
}
