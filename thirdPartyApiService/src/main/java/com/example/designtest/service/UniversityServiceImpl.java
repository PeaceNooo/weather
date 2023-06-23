package com.example.designtest.service;
import com.example.designtest.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService{
    private final RestTemplate restTemplate;

    @Value("${university.search.url}")
    private String universitySearchUrl;

    @Autowired
    public UniversityServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public List<University> getUniversityByName(String name) {System.out.println("universitySearchUrl: " + universitySearchUrl);
        List<University> universities = Arrays.asList(restTemplate.getForObject(
                universitySearchUrl+"?name=" + name,
                University[].class));


        return universities;
    }

    @Override
    public List<University> getUniversitiesByCountry(String country) {
        List<University> universities = Arrays.asList(restTemplate.getForObject(
                universitySearchUrl+"?country=" + country,
                University[].class));
        return universities;
    }
}
