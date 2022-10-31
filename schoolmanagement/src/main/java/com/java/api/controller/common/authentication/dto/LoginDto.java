package com.java.api.controller.common.authentication.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginDto {
    @NotEmpty
    @NotBlank
    private String email;

    @NotEmpty
    @NotBlank
    private String password;

    private Boolean isRememberMe;

}
