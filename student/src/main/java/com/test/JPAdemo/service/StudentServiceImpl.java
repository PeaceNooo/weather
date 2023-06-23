package com.test.JPAdemo.service;

import com.test.JPAdemo.dto.StudentDTO;
import com.test.JPAdemo.entity.Student;
import com.test.JPAdemo.exception.PeopleNotFoundException;
import com.test.JPAdemo.repo.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Long save(StudentDTO dto) {
        Student bean = new Student();
        BeanUtils.copyProperties(dto, bean);
        bean = studentRepository.save(bean);
        return bean.getStudentId();
    }

    public void delete(Long id) {
        studentRepository.findById(id).orElseThrow(() -> new PeopleNotFoundException("Student not found: " + id + " can not be deleted"));
        studentRepository.deleteById(id);
    }

    public StudentDTO update(Long id, StudentDTO dto) {
        Student bean = requireOne(id);
        BeanUtils.copyProperties(dto, bean);
        bean.setStudentId(id);
        studentRepository.save(bean);
        return toDTO(bean);
    }

    public StudentDTO getById(Long id) {
        Student original = requireOne(id);
        return toDTO(original);
    }

    public List<StudentDTO> findAll() {
        List<Student> original = studentRepository.findAll();
        return original.stream().map(this::toDTO).collect(Collectors.toList());
    }


    private StudentDTO toDTO(Student original) {
        StudentDTO bean = new StudentDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Student requireOne(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new PeopleNotFoundException("Student not found - " + id));
    }
}
