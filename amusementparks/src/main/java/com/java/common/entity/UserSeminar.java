package com.java.common.entity;

import com.java.enums.CommonEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_seminar")
public class UserSeminar extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "seminars_id")
    private Seminar seminar;

    @Column(name = "status", columnDefinition = "varchar(10)")
    private CommonEnum.StatusEnum status;
}
