package com.test.JPAdemo.entity;

import lombok.*;

import javax.persistence.*;

import java.util.List;

/**
 * $table.getTableComment()
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teacher")
public class Teacher {

    @Id
    @Column(name = "teacher_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<TeacherStudent> teacherStudents;
}
