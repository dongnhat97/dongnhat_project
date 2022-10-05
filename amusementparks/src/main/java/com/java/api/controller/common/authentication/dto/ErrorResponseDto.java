package com.java.api.controller.common.authentication.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponseDto {

    private String error_code;

    private String message;

}
