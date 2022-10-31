package com.java.common.entity;

import com.java.enums.CommonEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User extends BaseEntity {
    @Column(name = "user_name")
    private String userName;

    @JoinColumn(name = "status")
    private CommonEnum.StatusEnum status;

    @Column(columnDefinition = "text")
    @Size(min = 6)
    private String password;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "email")
    private String email;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")
    }
    )
    private List<Role> roles;
    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     *  inserted n-n relationship between Users and Seminars
     */
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_seminar", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "seminar_id", referencedColumnName = "id")})
    private List<Seminar> seminars;

    /**
     *  inserted 1-n relationship between Users and Login History
     */
    @OneToMany(mappedBy = "user")
    private List<LoginHistories> loginHistories;

    /**
     * insert n-n relationship between Users and Groups
     */
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_group", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "group_id", referencedColumnName = "id")})
    private List<Group> groups;

    /**
     * TaiHVK insert n-n relationship between Users and Notifications
     */
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_notifications", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "notifications_id", referencedColumnName = "id")})
    private List<Notifications> notifications;


}
