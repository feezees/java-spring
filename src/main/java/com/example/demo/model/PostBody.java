package com.example.demo.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;

@Embeddable
public class PostBody {
    @Column(name = "body_type")
    public String bodyType;
    
    @Column(name = "body_value")
    public String bodyValue;

    public PostBody() {
        // Default constructor required by JPA
    }

    public PostBody(String bodyType, String bodyValue){
        this.bodyType = bodyType;
        this.bodyValue = bodyValue;
    }
}