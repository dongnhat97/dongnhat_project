package com.java.common.entity;

import com.java.enums.CommonEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user_notifications")
public class UserNotifications extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "notifications_id")
    private Notifications notifications;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_read")
    private Integer isRead;

    @Column(name = "status", columnDefinition = "varchar(10)")
    private CommonEnum.StatusEnum status;
}
