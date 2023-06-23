package com.test.JPAdemo.service;

import com.test.JPAdemo.dto.StudentDTO;
import com.test.JPAdemo.dto.TeacherDTO;
import com.test.JPAdemo.dto.TeacherStudentDTO;

import java.util.List;

public interface TeacherStudentService {

        Long save(TeacherStudentDTO dto);

        TeacherStudentDTO getById(Long id);

        List<TeacherStudentDTO> findAll();

        List<TeacherDTO> getTeachersForStudent(Long studentId);

        void addTeacherToStudent(Long studentId, Long teacherId);

        void deleteTeacherFromStudent(Long studentId, Long teacherId);

        List<StudentDTO> getStudentsByTeacherId(Long teacherId);

        void addStudentToTeacher(Long teacherId, Long studentId);

        void deleteStudentFromTeacher(Long teacherId, Long studentId);
}
