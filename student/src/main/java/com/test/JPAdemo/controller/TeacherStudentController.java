package com.test.JPAdemo.controller;

import com.test.JPAdemo.dto.TeacherStudentDTO;
import com.test.JPAdemo.service.TeacherStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/teacherStudent")
public class TeacherStudentController {

    private TeacherStudentService teacherStudentService;

    @Autowired
    public TeacherStudentController(TeacherStudentService teacherStudentService) {
        this.teacherStudentService = teacherStudentService;
    }

    @PostMapping
    public String save(@Valid @RequestBody TeacherStudentDTO dto) {
        return teacherStudentService.save(dto).toString();
    }


    @GetMapping("/{id}")
    public TeacherStudentDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return teacherStudentService.getById(id);
    }

    @GetMapping
    public List<TeacherStudentDTO> findAll() {
        return teacherStudentService.findAll();
    }
}
