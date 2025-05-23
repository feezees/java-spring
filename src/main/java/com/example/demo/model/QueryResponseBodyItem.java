package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryResponseBodyItem {
    @JsonProperty("id")
    public String id;

    @JsonProperty("title")
    public String title;

    @JsonProperty("description")
    public String description;

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public QueryResponseBodyItem() {}
}
