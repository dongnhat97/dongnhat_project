package com.java.common.entity;

import com.java.enums.CommonEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserNotifications extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "notifications_id")
    private Notifications notifications;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User users;

    @Column(name = "is_read")
    private Integer isRead;

    @Column(name = "status", columnDefinition = "varchar(10)")
    private CommonEnum.StatusEnum status;
}
