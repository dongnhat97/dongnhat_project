package com.java.config.jwt;

import com.java.config.security.UserPrincipal;
import com.java.enums.TokenEnum;
import com.java.exception.BadRequestException;
import com.java.utils.constrains.APIConstants;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    public boolean validateJwtToken(String authToken) throws ServletException {
        try {
            // dont check expired
//            if(isTokenExpired(authToken)) {
//                throw new ServletException("Expired JWT token");
//            }
            Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            throw new ServletException("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            throw new ServletException("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            throw new ServletException("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            throw new ServletException("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            throw new ServletException("JWT claims string is empty");
        }
    }

    /**
     * Creates access token.
     *
     * @param authentication {@link Authentication}
     * @param rememberMe if application is remember me
     * @return {@link AccessToken}
     */

    public AccessToken createAccessToken(Authentication authentication, boolean rememberMe) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        String name = String.valueOf(principal.getUserId());
        long now = (new Date()).getTime();
        long dateToMilliseconds = 60*60*1000;
        Date validity;
        Date refreshTokenExpiration = new Date(now + TokenEnum.REFRESH_TOKEN_EXPIRED.getValue() * dateToMilliseconds);
        if (rememberMe) {
            validity = new Date(now + TokenEnum.TOKEN_REMEMBER_ME_EXPIRED.getValue() * dateToMilliseconds);
        } else {
            validity = new Date(now + TokenEnum.TOKEN_JWT_EXPIRED.getValue() * dateToMilliseconds);
        }
        //Build access token
        String jwt = Jwts.builder().setSubject(name)
                .signWith(SignatureAlgorithm.HS512, this.jwtSecret).compact();

        //Build refresh token
        String refreshToken = Jwts.builder().setSubject(name)
                .setExpiration(refreshTokenExpiration)
                .signWith(SignatureAlgorithm.HS512, this.jwtSecret)
                .compact();
        AccessToken accessToken = new AccessToken();
        accessToken.setToken(jwt);
        accessToken.setExpried(validity);
        accessToken.setRefreshToken(refreshToken);
        accessToken.setUserId(principal.getUserId());
        accessToken.setTokenType(APIConstants.JWT_TOKEN_TYPE);
        return accessToken;
    }


//    /**
//     * Create token register
//     *
//     * @param email
//     * @return token
//     */
//    public String createTokenRegister(String email) {
//        //Build access token
//        long now = (new Date()).getTime();
//        long dateToMilliseconds = 86400000 ;
//        Date validity = new Date(now + dateToMilliseconds);
//        String jwt = Jwts.builder().setSubject(email)
//                .setExpiration(validity)
//                .signWith(SignatureAlgorithm.HS512, this.jwtSecret).compact();
//        return jwt;
//    }

//    /**
//     * Get subject from input token
//     *
//     * @param token access token
//     * @return subject
//     */
//    public Integer getSubjectFromToken(String token) {
//        return Integer.valueOf(Jwts.parser()
//                .setSigningKey(this.jwtSecret)
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject());
//    }

    /**
     * Get Claim info from access token value.
     *
     * @param token the access token
     * @param claimKey Claim key
     * @return Claims info for claimKey
     */
    public Object getClaimInfo(String token, String claimKey) throws BadRequestException {
        Claims claims = Jwts.parser().
                setSigningKey(this.jwtSecret).
                parseClaimsJws(token).
                getBody();
        if (claims.get(claimKey) == null) {
            throw new BadRequestException(BadRequestException.ERROR_INVALID_TOKEN, APIConstants.TOKEN_INVALID_MSG, false);
        }
         return claims.get(claimKey);
    }

    /**
     * Is Token Expired
     *
     * @param token
     * @return
     */
    public Boolean isTokenExpired(String token) {
        try {
            final Date expiration = Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token).getBody().getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

//    public String getUserNameFromJwtToken(String token) {
//        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
//    }

//    /**
//     * build Claims token payment
//     *
//     * @param principal User Principal
//     * @return
//     */
//    private Map<String, Object> buildClaimsPayment(String user_id, Integer company_id, List<Integer> plan_id, Date date) {
//        Map<String, Object> claims = new HashMap<String, Object>();
//        claims.put("user_id",user_id);
//        claims.put("company_id", company_id);
//        claims.put("plan_id", plan_id);
//        claims.put("date", date);
//        return claims;
//    }

//    /**
//     * build Claims
//     *
//     * @param principal User Principal
//     * @return
//     */
//    private Map<String, Object> buildClaims(UserPrincipal principal) {
//        Map<String, Object> claims = new HashMap<String, Object>();
//        claims.put("userId", principal.getUserId());
//        claims.put("tfaChecked", principal.isTfaChecked());
//        return claims;
//    }

}
