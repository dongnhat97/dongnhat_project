package com.java.common.entity;

import javax.persistence.*;

@Entity
@Table(name = "param_caching")
public class ParamCaching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

}
