package com.java.common.entity;

import com.java.enums.CommonEnum;
import com.java.enums.StatusLoginEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The persistent class for the login_history database table.
 *
 */
@Table(name = "login_histories")
@Entity
@Getter
@Setter
public class LoginHistories implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    @CreationTimestamp
    private LocalDateTime date;

    @Enumerated(value = EnumType.STRING)
    private StatusLoginEnum.Status status;

    @Column(name = "access_token")
    private String accessToken;

    @Column(columnDefinition = "text")
    private String ip;

    @Column(columnDefinition = "text")
    private String browserName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at")
    @CreationTimestamp
    public LocalDateTime createdAt;

    @Column(name = "created_id")
    public String createdId;

}
