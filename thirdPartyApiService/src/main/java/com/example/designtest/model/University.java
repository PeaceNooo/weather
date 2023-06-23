package com.example.designtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class University {
    @JsonProperty("country")
    private String country;

    @JsonProperty("name")
    private String name;

    List<String> domains;

    @JsonProperty(value = "web_pages")
    private List<String> webPages;
}
