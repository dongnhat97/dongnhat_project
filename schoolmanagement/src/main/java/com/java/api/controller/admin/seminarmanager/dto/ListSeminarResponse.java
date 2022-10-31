package com.java.api.controller.admin.seminarmanager.dto;

import com.java.enums.CommonEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ListSeminarResponse {
    private String title;
    private String description;
    private String link;
    public LocalDateTime startDate;
    public LocalDateTime endDate;
    private CommonEnum.StatusEnum status;

}
