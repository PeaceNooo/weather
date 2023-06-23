package com.example.search.controller;

import com.example.search.service.SearchService;
import com.example.search.service.StudentServiceProxy;
import com.example.search.service.ThirdPartyServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SearchController {

    private final SearchService searchService;


    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;

    }

    @GetMapping("/weather/search")
    public ResponseEntity<?> getDetails() {

        HashMap<String, List<Map<String, Object>>> result = searchService.search();

        return ResponseEntity.ok(result);
    }
}
