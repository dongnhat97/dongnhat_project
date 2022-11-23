package com.java.teacher.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
@Getter
@Setter
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private static final long serialVersionUID = 1L;
    @Column(name = "title")
    private String title;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private Double price;
    @Column(name = "total_money")
    private Double totalMoney;

    public Book(Integer id, String title, Integer quantity, Double price) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }
}
