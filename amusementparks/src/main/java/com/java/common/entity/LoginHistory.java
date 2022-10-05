package com.java.common.entity;


import com.java.enums.CommonEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * [OVERVIEW] The persistent class for the login_history database table.
 *
 * @author: BAP(NhatDV)
 * @version: 1.0
 * @History [NUMBER] [VER] [DATE] [USER] [CONTENT]
 * -----------------------------------------
 * 001 1.0 2021/12/22 BAP(NhatDV) Create new
 */
@Table(name = "login_history")
@Entity
@Getter
@Setter

public class LoginHistory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User user;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "status")
    private CommonEnum.StatusEnum status;

    @Column(name = "ip")
    private String ip;

    @Column(name = "browser_name")
    private String browserName;
    
}
