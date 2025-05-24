package com.example.demo.model;

import org.springframework.http.HttpStatus;

public class FailedResonpseBody {
    public HttpStatus errorText;

    public FailedResonpseBody(HttpStatus errorText){
        this.errorText = errorText;
    }

    public FailedResonpseBody(){

    }
}
