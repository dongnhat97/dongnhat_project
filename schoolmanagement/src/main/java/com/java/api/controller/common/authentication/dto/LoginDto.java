package com.java.api.controller.common.authentication.dto;

import com.java.common.constant.CommonConstant;
import com.java.validator.constraint.PasswordConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginDto {
    @NotEmpty
    @NotBlank
    private String email;

    //    @NotEmpty
//    @NotBlank
//    @Size(min = 8)
    @PasswordConstraint(format = CommonConstant.REGEX_PATTERN.TEST_PASSWORD)
    private String password;
    private Boolean isRememberMe;

}
