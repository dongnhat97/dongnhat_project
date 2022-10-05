package com.java.common.entity;

import com.java.enums.CommonEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import java.util.List;
@Entity
@Getter
@Setter
public class Group extends BaseEntity{
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;

    @Column(nullable = false)
    @NotEmpty
    private String detail;

    private CommonEnum.StatusEnum status;

    @ManyToMany(mappedBy = "groups")
    List<User> users;
}
