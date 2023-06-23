package com.test.JPAdemo.controller;

import com.test.JPAdemo.dto.StudentDTO;
import com.test.JPAdemo.dto.TeacherDTO;
import com.test.JPAdemo.service.TeacherService;
import com.test.JPAdemo.service.TeacherStudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private TeacherService teacherService;

    private final TeacherStudentService teacherStudentService;

    @Autowired
    public TeacherController(TeacherService teacherService, TeacherStudentService teacherStudentService) {
        this.teacherService = teacherService;
        this.teacherStudentService = teacherStudentService;
    }

    // Create a new teacher
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TeacherDTO dto) {
        long id = teacherService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();

        return ResponseEntity.created(location).build();
    }

    // Delete a teacher by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @NotNull @PathVariable("id") Long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Update a teacher by id
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody TeacherDTO dto) {
        TeacherDTO updatedTeacher = teacherService.update(id, dto);
        return ResponseEntity.ok(updatedTeacher);
    }

    // Get a teacher by id
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getById(@Valid @NotNull @PathVariable("id") Long id) {
        TeacherDTO teacher = teacherService.getById(id);
        return ResponseEntity.ok(teacher);
    }

    // Get all teachers
    @GetMapping
    public ResponseEntity<List<TeacherDTO>> findAll() {
        List<TeacherDTO> teachers = teacherService.findAll();
        return ResponseEntity.ok(teachers);
    }

    // Get all students for a teacher
    @GetMapping("/{teacherId}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsByTeacherId(@PathVariable Long teacherId) {
        List<StudentDTO> students = teacherStudentService.getStudentsByTeacherId(teacherId);
        return ResponseEntity.ok(students);
    }


    // Add a new student to a teacher
    @PostMapping("/{teacherId}/students/{studentId}")
    public ResponseEntity<String> addStudentToTeacher(@PathVariable Long teacherId, @PathVariable Long studentId) {
        teacherStudentService.addStudentToTeacher(teacherId, studentId);
        return ResponseEntity.ok("Student with id " + studentId + " was added to teacher with id " + teacherId);
    }

    // Delete a student from a teacher
    @DeleteMapping("/{teacherId}/students/{studentId}")
    public ResponseEntity<Void> deleteStudentFromTeacher(@PathVariable Long teacherId, @PathVariable Long studentId) {
        teacherStudentService.deleteStudentFromTeacher(teacherId, studentId);
        return ResponseEntity.noContent().build();

    }

}
