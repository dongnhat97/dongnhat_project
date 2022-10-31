package com.java.api.controller.common.authentication.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    public LoginRequest(@NotBlank String userName, @NotBlank String password) {
        this.userName = userName;
        this.password = password;
    }
}
