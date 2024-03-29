package com.java.api.controller.common.authentication;

import com.java.api.controller.common.authentication.dto.LoginDto;
import com.java.api.controller.common.authentication.dto.UserAuthenticationDto;
import com.java.common.entity.LoginHistories;
import com.java.common.entity.Role;
import com.java.common.entity.User;
import com.java.common.repository.LoginHistoryRepository;
import com.java.common.repository.UserRepository;
import com.java.common.service.BaseService;
import com.java.common.service.MessageService;
import com.java.config.jwt.AccessToken;
import com.java.config.security.BaseUserDetailsService;
import com.java.config.security.UserPrincipal;
import com.java.enums.CommonEnum;
import com.java.enums.StatusLoginEnum;
import com.java.exception.BadRequestException;
import com.java.exception.NotFoundException;
import com.java.utils.constrains.APIConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

//import com.java.common.entity.VerifyMail;
//import com.java.common.mail.MailSenderImpl;
//import com.java.common.mail.MailService;
//import com.java.common.repository.VerifyMailRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService extends BaseService {
    private final MessageService messageService;
    private final UserRepository userRepository;
    private final BaseUserDetailsService userDetailsService;
    private final LoginHistoryRepository loginHistoryRepository;
    private final PasswordEncoder passwordEncoder;
//    private final VerifyMailRepository verifyMailRepository;
//    private final MailSenderImpl mailSender;
//    private final MailService mailService;


    /**
     * Save info login
     *
     * @param user
     * @param request
     * @param status
     * @return AccessToken
     * @throws NotFoundException
     */
    private void storeLoginHistory(User user, HttpServletRequest request, StatusLoginEnum.Status status) throws NotFoundException {
        LoginHistories loginHistories = new LoginHistories();
        loginHistories.setDate(LocalDateTime.now());
        loginHistories.setStatus(status);
        loginHistories.setUser(user);
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null || "".equals(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        if (ipAddress != null) {
            loginHistories.setIp(ipAddress.split(",")[0]);
        } else {
            loginHistories.setIp("");
        }
        loginHistories.setBrowserName(request.getHeader("User-Agent"));
        loginHistories.setCreatedId(String.valueOf(user.getId()));
        loginHistoryRepository.save(loginHistories);
    }


    /**
     * Get jwt token from user sign in
     *
     * @param loginDto {@link LoginDto}
     * @return UserAuthenticationDTO
     * @throws Exception
     */
    public Object loginService(LoginDto loginDto, HttpServletRequest request) throws Exception {
        UserAuthenticationDto userAuthenticationDto = new UserAuthenticationDto();
        Optional<User> optionalUser = Optional.ofNullable(this.userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new NotFoundException(NotFoundException.ERROR_USER_NOT_FOUND,
                        APIConstants.NOT_FOUND_MESSAGE.replace(APIConstants.REPLACE_CHAR, APIConstants.USER))));

//        if (!new BCryptPasswordEncoder().matches(loginDto.getPassword(), optionalUser.get().getPassword())) {
//            throw new BadRequestException(BadRequestException.ERROR_AUTHENTICATION_FAILED_WRONG_EMAIL_OR_PASSWORD,
//                    APIConstants.ERROR_PASSWORD_WRONG, false);
//        }
        if (optionalUser.get().getStatus() == CommonEnum.StatusEnum.DELETED) {
            storeLoginHistory(optionalUser.get(), request, StatusLoginEnum.Status.FAILED);
            throw new BadRequestException(BadRequestException.ERROR_USER_HAS_BEEN_DELETED,
                    this.messageService.buildMessages("error.msg.user-login-has-been-deleted"), false);
        }

        if (optionalUser.get().getStatus() != CommonEnum.StatusEnum.ACTIVE) {
            storeLoginHistory(optionalUser.get(), request, StatusLoginEnum.Status.FAILED);
            throw new BadRequestException(BadRequestException.ERROR_USER_NOT_ACTIVATED,
                    this.messageService.buildMessages("error.msg.user-not-activated"), false);
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(new UserPrincipal()
                .create(optionalUser.get(), this.userDetailsService.getAuthorities(optionalUser.get())), null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        Boolean isRemember = loginDto.getIsRememberMe() == null ? false
//                : loginDto.getIsRememberMe() == false ? false : true;
        AccessToken accessToken = this.jwtForAPIResponse(authentication, true);
        userAuthenticationDto.setAccessToken(accessToken.getToken());
        userAuthenticationDto.setTokenType(APIConstants.JWT_TOKEN_TYPE);
        userAuthenticationDto.setRefreshToken(accessToken.getRefreshToken());
        userAuthenticationDto.setUserId(optionalUser.get().getId());
        userAuthenticationDto
                .setRoles(optionalUser.get().getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        // history
        storeLoginHistory(optionalUser.get(), request, StatusLoginEnum.Status.SUCCESS);
        return userAuthenticationDto;
    }


//    public Object registerAccount(AccountDto accountDto) throws Exception {
//        User user = this.userRepository.findByUserName(accountDto.getName());
//        if (user != null) {
//            throw new IllegalStateException("username had already exist");
//        }
//        user = this.userRepository.findUserByEmail(accountDto.getEmail());
//        if (user != null) {
//            throw new IllegalStateException("email had already exist");
//        }
//        String encoder = passwordEncoder.encode(accountDto.getPassword());
//        accountDto.setPassword(encoder);
//        User newUser = new User();
//        BeanUtils.copyProperties(accountDto, newUser);
//        newUser.setStatus(CommonEnum.StatusEnum.DELETED);
//        userRepository.save(newUser);
//
//        String token = UUID.randomUUID().toString();
//        VerifyMail verifyMail = new VerifyMail(token, accountDto.getEmail(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
//        verifyMailRepository.save(verifyMail);
//        String link = "http://localhost:8080/api/authentication/confirm?token=" + token;
//        mailSender.buildEmail(link);
//        mailSender.send(accountDto.getEmail(), link);
//        return APIErrorResponse.createdStatus(CommonConstant.ERROR_NAMES.NG, null, null, HttpStatus.OK);
//
//    }
//
//    public Object confirmEmail(String token) throws NotFoundException {
//        mailService.confirmMail(token);
//        return APIResponse.successfulRegister("Congratulations, you have successfully registered", HttpStatus.OK);
//    }
}

