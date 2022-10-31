package com.java.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "course")
public class Course extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "overview")
    private String overview;

    @ManyToOne
    @JoinColumn(name = "course_categort_id")
    CourseCategory courseCategory;

    @ManyToMany
    @JoinTable(name = "student_course", joinColumns = {
            @JoinColumn(name = "course_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "student_id", referencedColumnName = "id")
            })
    private List<Student> studentList;
}
