package com.java.api.controller.admin.notificationmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.common.constant.CommonConstant;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class NotificationResponse {
    /**
     * The id of Notification
     */
    @JsonProperty("id")
    private Integer id;

    /**
     * The title of Notification
     */
    @JsonProperty("title")
    private String title;

    /**
     * creation notification start time
     * format: yyyy/mm/dd HH:mm
     */
    @JsonProperty("publication_start")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.REGEX_PATTERN.YYYY_MM_DD_HH_MM_SPLASH)
    private LocalDateTime publicationStart;

    /**
     * status of notification
     */
    @JsonProperty("status")
    private String status;

    /**
     * name user post notification
     */
    @JsonProperty("name_user_post")
    private String nameUserPost;


}
