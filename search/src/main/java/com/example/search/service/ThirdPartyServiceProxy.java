package com.example.search.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "thirdPartyApiService")
@RibbonClient(name = "thirdPartyApiService")
public interface ThirdPartyServiceProxy {

    //get university by name
    @GetMapping("/universities/search")
    ResponseEntity<List<Map<String, Object>>> getUniversityByName(@RequestParam String name);

    //get university by country
    @GetMapping("/universities/search")
    ResponseEntity<List<Map<String, Object>>> getUniversitiesByCountry(@RequestParam String country);
}
