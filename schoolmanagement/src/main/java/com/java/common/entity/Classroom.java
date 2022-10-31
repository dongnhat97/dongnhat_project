package com.java.common.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "classroom")
public class Classroom  extends BaseEntity{
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "classroom")
    @JsonBackReference
    private List<Student>studentList;
}
