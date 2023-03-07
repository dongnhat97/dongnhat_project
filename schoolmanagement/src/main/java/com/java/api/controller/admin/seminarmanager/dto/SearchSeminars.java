package com.java.api.controller.admin.seminarmanager.dto;

import com.java.enums.CommonEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SearchSeminars {
    private Integer id;
    private Integer userId;
    private String title;
    private String description;
    private String link;
    public LocalDateTime startDate;
    public LocalDateTime endDate;
    private String status;

    public SearchSeminars(ISeminarResponse seminarResponse) {
        this.id = seminarResponse.getId();
        this.userId = seminarResponse.getUserId();
        this.title = seminarResponse.getTitle();
        this.description = seminarResponse.getDescription();
        this.link = seminarResponse.getLink();
        this.startDate = seminarResponse.getEndDate();
        this.endDate = seminarResponse.getEndDate();
        this.status = seminarResponse.getStatus();
    }


}
