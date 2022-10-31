package com.java.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "course_category")
public class CourseCategory extends BaseEntity {
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "courseCategory",fetch = FetchType.LAZY)
    private List<Course> courseList;
}
