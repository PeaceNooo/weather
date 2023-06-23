package com.test.JPAdemo.service;

import com.test.JPAdemo.dto.TeacherDTO;
import com.test.JPAdemo.entity.Teacher;
import com.test.JPAdemo.exception.PeopleNotFoundException;
import com.test.JPAdemo.repo.TeacherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService{

    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Long save(TeacherDTO dto) {
        Teacher bean = new Teacher();
        BeanUtils.copyProperties(dto, bean);
        bean = teacherRepository.save(bean);
        return bean.getTeacherId();
    }

    public String delete(Long id) {
        teacherRepository.findById(id).orElseThrow(() -> new PeopleNotFoundException("Teacher not found: " + id + " can not be deleted"));
        teacherRepository.deleteById(id);
        return "Teacher with id " + id + " was deleted";
    }

    public TeacherDTO update(Long id, TeacherDTO dto) {
        Teacher bean = requireOne(id);
        BeanUtils.copyProperties(dto, bean);
        bean.setTeacherId(id);
        teacherRepository.save(bean);
        return toDTO(bean);
    }

    public TeacherDTO getById(Long id) {
        Teacher original = requireOne(id);
        return toDTO(original);
    }

    public List<TeacherDTO> findAll() {
        List<Teacher> original = teacherRepository.findAll();
        return original.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private TeacherDTO toDTO(Teacher original) {
        TeacherDTO bean = new TeacherDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Teacher requireOne(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new PeopleNotFoundException("Teacher not found: " + id));
    }
}
