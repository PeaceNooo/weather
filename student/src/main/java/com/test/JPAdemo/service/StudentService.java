package com.test.JPAdemo.service;

import com.test.JPAdemo.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    Long save(StudentDTO dto);

    void delete(Long id);

    StudentDTO update(Long id, StudentDTO dto);

    StudentDTO getById(Long id);

    List<StudentDTO> findAll();

}

