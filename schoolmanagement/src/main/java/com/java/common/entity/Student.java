package com.java.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "student")
public class Student extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;
    @Column(name = "address")
    private String address;
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;
}
