package com.java.api.controller.common.authentication.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    public LoginRequest(@NotBlank String loginId, @NotBlank String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
