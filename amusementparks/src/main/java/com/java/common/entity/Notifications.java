package com.java.common.entity;

import com.java.enums.CommonEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Notifications extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "text", columnDefinition = "varchar(8000)")
    private String text;

    @Column(name = "publication_start", columnDefinition = "timestamp")
    private LocalDateTime publicationStart;

    @Column(name = "publication_end", columnDefinition = "timestamp")
    private LocalDateTime publicationEnd;

    @Column(name = "status", columnDefinition = "varchar(10)")
    private CommonEnum.StatusEnum status;
}
