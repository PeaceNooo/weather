package com.example.designtest.service;

import com.example.designtest.model.University;

import java.util.List;

public interface UniversityService {
    List<University> getUniversityByName(String name);
    List<University> getUniversitiesByCountry(String country);


}
