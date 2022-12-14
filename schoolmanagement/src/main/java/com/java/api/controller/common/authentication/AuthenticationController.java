package com.java.api.controller.common.authentication;

import com.java.api.controller.common.authentication.dto.AccountDto;
import com.java.api.controller.common.authentication.dto.LoginDto;
import com.java.common.constant.CommonConstant;
import com.java.common.response.APIErrorResponse;
import com.java.common.response.APIResponse;
import com.java.exception.NotFoundException;
import com.java.utils.BindingResultUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private final BindingResultUtils bindingResultUtils;
    private final AuthenticationService authenticationService;


    @PostMapping("/login")
    public Object login(@Valid @RequestBody LoginDto loginDto, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        if (bindingResult.hasErrors()) {
            Map<String, String> mapError = bindingResultUtils.errorMapBindingResultUtils(bindingResult);
            return APIErrorResponse.errorStatus(CommonConstant.ERROR_NAMES.NG, null, mapError, HttpStatus.BAD_REQUEST);
        }
        return APIResponse.okStatus(authenticationService.loginService(loginDto,request));

    }
//    @PostMapping("/register")
//    public Object registerAccount(@Valid @RequestBody AccountDto accountDto,BindingResult bindingResult) throws Exception {
//        if (bindingResult.hasErrors()) {
//            Map<String,String>  mapError = bindingResultUtils.errorMapBindingResultUtils(bindingResult);
//            return APIErrorResponse.errorStatus(CommonConstant.ERROR_NAMES.NG,null,mapError,HttpStatus.BAD_REQUEST);
//        }
//       return APIResponse.okStatus(authenticationService.registerAccount(accountDto));
//    }
//
//    @PostMapping("/confirm")
//    public Object confirmEmail(@RequestParam String token) throws NotFoundException {
//        return APIResponse.okStatus(authenticationService.confirmEmail(token));
//    }
}