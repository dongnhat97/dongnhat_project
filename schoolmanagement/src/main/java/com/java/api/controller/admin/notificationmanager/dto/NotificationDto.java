package com.java.api.controller.admin.notificationmanager.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.common.constant.CommonConstant;
import com.java.validator.constraint.DateConstraint;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
public class NotificationDto {


    /**
     * The title of Notification
     */
    @JsonProperty("title")
    @NotNull
    @NotEmpty
    private String title;

    /**
     * The content of Notification
     */
    @JsonProperty("text")
    @NotNull
    @NotEmpty
    private String text;

    /**
     * creation notification start time
     * format: yyyy/mm/dd HH:mm
     */
    @JsonProperty("publication_start")
    @DateConstraint(format = CommonConstant.REGEX_PATTERN.REX_DATE_TIME_YYYY_MM_DD_HH_MM_SPLASH)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/dd/mm HH:mm")
    private String publicationStart;

    /**
     * creation notification end time
     * format: yyyy/mm/dd HH:mm
     */
    @JsonProperty("publication_end")
    @DateConstraint(format = CommonConstant.REGEX_PATTERN.REX_DATE_TIME_YYYY_MM_DD_HH_MM_SPLASH)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/dd/mm HH:mm")
    private String publicationEnd;

    /**
     * list id of users receiving notifications
     */
    @JsonProperty("list_id_user")
    public List<Integer> listIdUser;
}
