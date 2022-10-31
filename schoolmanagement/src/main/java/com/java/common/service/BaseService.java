package com.java.common.service;

import com.java.config.jwt.AccessToken;
import com.java.config.jwt.JwtProvider;
import com.java.config.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    @Autowired
    private JwtProvider jwtProvider;

    /**
     * function get userID in Jwt
     *
     * @return userId
     */
    public Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = null;
        if (principal instanceof UserDetails) {
            userId = ((UserPrincipal) principal).getUserId();
        } else if (!principal.equals("anonymousUser")) {
            userId = Integer.parseInt((String) principal);
        }
        return userId;

    }

    /**
     * Create JwtToken
     *
     * @param authentication
     * @param isRememberMe
     * @return
     */
    public AccessToken jwtForAPIResponse(Authentication authentication, boolean isRememberMe) {
        return this.jwtProvider.createAccessToken(authentication, isRememberMe);
    }


}
