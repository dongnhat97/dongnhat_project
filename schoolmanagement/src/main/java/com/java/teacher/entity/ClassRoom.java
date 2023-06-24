package com.java.teacher.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "class_room")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoom {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    private static final long serialVersionUID = 1L;
    @Column(name = "classe_name")
    private String className;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "school")
    private String school;
    @Column(name = "teacher_id")
    private Integer teacherId;

}
