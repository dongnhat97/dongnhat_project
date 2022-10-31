package com.java.api.controller.common.authentication.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class UserAuthenticationDto {

    @ApiModelProperty(readOnly = true)
    private String accessToken;

    @ApiModelProperty(readOnly = true)
    private String tokenType;

    @NotNull()
    @NotEmpty()
    private String refreshToken;

    @ApiModelProperty(readOnly = true)
    private Integer userId;

    @ApiModelProperty(readOnly = true)
    private List<String> roles;
}
