package com.java.api.controller.admin.seminarmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.common.constant.CommonConstant;
import com.java.enums.CommonEnum;
import com.java.validator.constraint.DateConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateSeminarDto {
    @JsonProperty("id")
    @NotNull
    private Integer id;

    @JsonProperty("title")
    @NotNull
    @NotEmpty
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("link")
    @NotNull
    @NotEmpty
    private String link;

    @JsonProperty("start_date")
    @DateConstraint(format = CommonConstant.REGEX_PATTERN.REX_DATE_TIME_YYYY_MM_DD_HH_MM_SPLASH)
    public String startDate;

    @JsonProperty("end_date")
    @DateConstraint(format = CommonConstant.REGEX_PATTERN.REX_DATE_TIME_YYYY_MM_DD_HH_MM_SPLASH)
    public String endDate;

    @JsonProperty("status")
    private CommonEnum.StatusEnum status;
}
