package com.java.api.controller.common.authentication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    private String email;

    private String password;

    private Boolean isRememberMe;

}
