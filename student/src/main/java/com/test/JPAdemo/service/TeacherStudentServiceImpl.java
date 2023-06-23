package com.test.JPAdemo.service;

import com.test.JPAdemo.dto.StudentDTO;
import com.test.JPAdemo.dto.TeacherDTO;
import com.test.JPAdemo.dto.TeacherStudentDTO;
import com.test.JPAdemo.entity.Student;
import com.test.JPAdemo.entity.Teacher;
import com.test.JPAdemo.entity.TeacherStudent;
import com.test.JPAdemo.exception.PeopleNotFoundException;
import com.test.JPAdemo.repo.StudentRepository;
import com.test.JPAdemo.repo.TeacherRepository;
import com.test.JPAdemo.repo.TeacherStudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherStudentServiceImpl implements TeacherStudentService{

    private TeacherStudentRepository teacherStudentRepository;

    private StudentRepository studentRepository;

    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherStudentServiceImpl(TeacherStudentRepository teacherStudentRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.teacherStudentRepository = teacherStudentRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public Long save(TeacherStudentDTO dto) {
        TeacherStudent bean = new TeacherStudent();
        BeanUtils.copyProperties(dto, bean);
        bean = teacherStudentRepository.save(bean);
        return bean.getId();
    }

    public TeacherStudentDTO getById(Long id) {
        TeacherStudent original = requireOne(id);
        return toDTO(original);
    }

    public List<TeacherStudentDTO> findAll() {
        List<TeacherStudent> original = teacherStudentRepository.findAll();
        return original.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<TeacherDTO> getTeachersForStudent(Long studentId) {
        // check if student exists
        studentRepository.findById(studentId)
                .orElseThrow(() -> new PeopleNotFoundException("Student not found - " + studentId));
        List<Teacher> original = teacherStudentRepository.findTeacherByStudentStudentId(studentId);
        return original.stream().map(this::toTeacherDTO).collect(Collectors.toList());
    }

    @Override
    public void addTeacherToStudent(Long studentId, Long teacherId) {
        // Fetch the Student and Teacher entities
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new PeopleNotFoundException("Student not found - " + studentId));
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new PeopleNotFoundException("Teacher not found - " + teacherId));

        // Create a new TeacherStudent entity
        TeacherStudent teacherStudent = new TeacherStudent();
        teacherStudent.setStudent(student);
        teacherStudent.setTeacher(teacher);

        // Save the entity
        teacherStudentRepository.save(teacherStudent);
    }

    @Override
    public void deleteTeacherFromStudent(Long studentId, Long teacherId) {
        // Fetch the Student and Teacher entities
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new PeopleNotFoundException("Student not found - " + studentId));
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new PeopleNotFoundException("Teacher not found - " + teacherId));

        // Find the TeacherStudent entity for the student and teacher
        Optional<TeacherStudent> teacherStudentOptional = teacherStudentRepository.findByStudentAndTeacher(student, teacher);

        // If the entity exists, delete it
        teacherStudentOptional.ifPresent(teacherStudentRepository::delete);
    }

    @Override
    public List<StudentDTO> getStudentsByTeacherId(Long teacherId) {
        // check if teacher exists
        teacherRepository.findById(teacherId)
                .orElseThrow(() -> new PeopleNotFoundException("Teacher not found - " + teacherId));
        List<Student> original = teacherStudentRepository.findStudentByTeacherTeacherId(teacherId);

        return original.stream().map(this::toStudentDTO).collect(Collectors.toList());
    }

    @Override
    public void addStudentToTeacher(Long teacherId, Long studentId) {
        // Fetch the Student and Teacher entities
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new PeopleNotFoundException("Student not found - " + studentId));
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new PeopleNotFoundException("Teacher not found - " + teacherId));

        // Create a new TeacherStudent entity
        TeacherStudent teacherStudent = new TeacherStudent();
        teacherStudent.setStudent(student);
        teacherStudent.setTeacher(teacher);

        // Save the entity
        teacherStudentRepository.save(teacherStudent);
    }

    @Override
    public void deleteStudentFromTeacher(Long teacherId, Long studentId) {
        // Fetch the Student and Teacher entities
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new PeopleNotFoundException("Student not found - " + studentId));
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new PeopleNotFoundException("Teacher not found - " + teacherId));

        // Find the TeacherStudent entity for the student and teacher
        Optional<TeacherStudent> teacherStudentOptional = teacherStudentRepository.findByStudentAndTeacher(student, teacher);

        // If the entity exists, delete it
        teacherStudentOptional.ifPresent(teacherStudentRepository::delete);
    }

    private TeacherStudentDTO toDTO(TeacherStudent original) {
        TeacherStudentDTO bean = new TeacherStudentDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private TeacherDTO toTeacherDTO(Teacher teacher) {
        TeacherDTO dto = new TeacherDTO();
        BeanUtils.copyProperties(teacher, dto);
        return dto;
    }

    private StudentDTO toStudentDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        BeanUtils.copyProperties(student, dto);
        return dto;
    }

    private TeacherStudent requireOne(Long id) {
        return teacherStudentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }


}
