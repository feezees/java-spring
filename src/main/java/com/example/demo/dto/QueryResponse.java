package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.demo.model.QueryResponseBodyItem;

public class QueryResponse {
    @JsonProperty("data")
    public QueryResponseBodyItem[] data;

    public QueryResponseBodyItem[] getData() {
        return this.data;
    }

    public QueryResponse() {}
}
