package com.java.common.entity;


import com.java.enums.CommonEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
@Table(name = "seminar")
@Entity
@Getter
@Setter
public class Seminar extends BaseEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "link")
    private String link;

    @Column(name = "start_date")
    @CreationTimestamp
    public LocalDateTime startDate;

    @Column(name = "end_date")
    @CreationTimestamp
    public LocalDateTime endDate;

    @Column(name = "status", columnDefinition = "varchar(10)")
    private CommonEnum.StatusEnum status;


}
