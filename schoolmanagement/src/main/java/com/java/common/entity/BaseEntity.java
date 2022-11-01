package com.java.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(BaseEntity.class)
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private static final long serialVersionUID = 1L;

    @Column(name = "created_at")
    @CreationTimestamp
    public LocalDateTime createdAt;

    @Column(name = "created_id")
    public Integer createdId;

    @Column(name = "modified_at")
    @UpdateTimestamp
    public LocalDateTime modifiedAt;

    @Column(name = "modified_id")
    public Integer modifiedId;

}
