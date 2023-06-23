package com.example.search.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name = "student")
@RibbonClient(name = "student")
public interface StudentServiceProxy {

    // get student by id
    @GetMapping("/students/{studentId}")
    String getStudentById(Long studentId);

    // get all students
    @GetMapping("/students")
    ResponseEntity<List<Map<String, Object>>> getAllStudents();
}
