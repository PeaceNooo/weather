package com.test.JPAdemo.service;

import com.test.JPAdemo.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {

        Long save(TeacherDTO dto);

        String delete(Long id);

        TeacherDTO update(Long id, TeacherDTO dto);

        TeacherDTO getById(Long id);

        List<TeacherDTO> findAll();
}
