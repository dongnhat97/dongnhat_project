package com.java.common.entity;

import com.java.enums.CommonEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.List;
@Entity
@Getter
@Setter
@Table(name = "`group`")
public class Group extends BaseEntity{
    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "detail")
    @NotEmpty
    private String detail;

    private CommonEnum.StatusEnum status;

}
