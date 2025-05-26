package com.example.demo.model;

import org.springframework.http.HttpStatus;

public class FailedResponseBody {
    public HttpStatus errorText;

    public FailedResponseBody(HttpStatus errorText){
        this.errorText = errorText;
    }

    public FailedResponseBody(){

    }
}
