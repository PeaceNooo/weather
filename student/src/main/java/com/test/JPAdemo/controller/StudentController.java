package com.test.JPAdemo.controller;

import com.test.JPAdemo.dto.StudentDTO;
import com.test.JPAdemo.dto.TeacherDTO;
import com.test.JPAdemo.service.StudentService;
import com.test.JPAdemo.service.TeacherStudentService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    private final TeacherStudentService teacherStudentService;

    @Autowired
    public StudentController(StudentService studentService, TeacherStudentService teacherStudentService) {
        this.studentService = studentService;
        this.teacherStudentService = teacherStudentService;
    }

    // Create a new student
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody StudentDTO dto) {
        Long id = studentService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    // Delete a student by studentId
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> delete(@Valid @NotNull @PathVariable("studentId") Long studentId) {
        studentService.delete(studentId);
        return ResponseEntity.noContent().build();
    }

    // Update a student by studentId
    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDTO> update(@Valid @NotNull @PathVariable("studentId") Long studentId,
                                             @Valid @RequestBody StudentDTO dto) {
        StudentDTO updatedStudent = studentService.update(studentId, dto);
        return ResponseEntity.ok(updatedStudent);
    }

    // Get a student by studentId
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getById(@Valid @NotNull @PathVariable("studentId") Long studentId) {
        StudentDTO student = studentService.getById(studentId);
        return ResponseEntity.ok(student);
    }

    // Get all students
    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAll() {
        List<StudentDTO> students = studentService.findAll();
        return ResponseEntity.ok(students);
    }

    // Get all teachers for a student
    @GetMapping("/{studentId}/teachers")
    public ResponseEntity<List<TeacherDTO>> getTeachersForStudent(@PathVariable Long studentId) {
        List<TeacherDTO> teachers = teacherStudentService.getTeachersForStudent(studentId);
        return ResponseEntity.ok(teachers);
    }

    // Add a new teacher to a student
    @PostMapping("/{studentId}/teachers/{teacherId}")
    public ResponseEntity<String> addTeacherToStudent(@PathVariable Long studentId, @PathVariable Long teacherId) {
        teacherStudentService.addTeacherToStudent(studentId, teacherId);
        return ResponseEntity.ok("Teacher with id " + teacherId + " was added to student with id " + studentId);
    }

    // Delete a teacher from a student
    @DeleteMapping("/{studentId}/teachers/{teacherId}")
    public ResponseEntity<Void> deleteTeacherFromStudent(@PathVariable Long studentId, @PathVariable Long teacherId) {
        teacherStudentService.deleteTeacherFromStudent(studentId, teacherId);
        return ResponseEntity.noContent().build();
    }
}
