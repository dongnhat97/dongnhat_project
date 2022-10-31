package bap.training.api.controller.admin.notificationmanagement.dto;

import bap.training.common.constant.CommonConstant;
import bap.training.validator.constraint.DateConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * [OVERVIEW] NotificationDto
 *
 * @author: BAP(KhueHD)
 * @version: 1.0
 * @History [NUMBER] [VER] [DATE] [USER] [CONTENT]
 * -----------------------------------------
 * 001 1.0 2021/12/27 BAP(KhueHD) Create new
 */
@Getter
@Setter
@RequiredArgsConstructor
public class NotificationDto {

    /**
     * The id of Notification
     */
    @JsonProperty("id")
    private Integer id;

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
