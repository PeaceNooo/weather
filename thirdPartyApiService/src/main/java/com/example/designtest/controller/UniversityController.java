package com.example.designtest.controller;

import com.example.designtest.model.University;
import com.example.designtest.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {
    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping(value = "/search", params = "name")
    public ResponseEntity<List<University>> getUniversityByName(@RequestParam String name) {
        List<University> universities = universityService.getUniversityByName(name);
        return ResponseEntity.ok(universities);
    }

    @GetMapping(value = "/search", params = "country")
    public ResponseEntity<List<University>> getUniversitiesByCountry(@RequestParam String country) {
        List<University> universities = universityService.getUniversitiesByCountry(country);
        return ResponseEntity.ok(universities);
    }

}
