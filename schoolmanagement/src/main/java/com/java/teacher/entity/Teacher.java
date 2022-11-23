package com.java.teacher.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "teacher")
public class Teacher implements Serializable {


    @Id
    private Integer id;
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;

    public Teacher() {
    }

    public Teacher(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
