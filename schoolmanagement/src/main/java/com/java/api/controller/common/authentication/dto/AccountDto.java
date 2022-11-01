package com.java.api.controller.common.authentication.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AccountDto {
    @NotEmpty
    @NotNull
    String name;
    @NotEmpty
    @NotNull
    String email;
    @NotEmpty
    @NotNull
    String password;

    String address;
    String phoneNumber;

}
