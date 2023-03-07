package com.java.api.controller.admin.seminarmanager.dto;

import com.java.common.entity.User;
import com.java.enums.CommonEnum;

import java.time.LocalDateTime;

public interface ISeminarResponse {
    String getTitle();

    String getDescription();

    String getLink();

    String getStatus();

    Integer getId();

    Integer getUserId();

    LocalDateTime getStartDate();

    LocalDateTime getEndDate();
}
