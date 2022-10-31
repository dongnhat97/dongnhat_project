package com.java.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Authentication Exception
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class AuthenticationException extends Exception {

    public static final String UNAUTHORIZED_USER_INACTIVE = "UNAUTHORIZED_USER_INACTIVE";

    public static final String ERROR_USER_NOT_FOUND_IN_SYSTEM = "ERROR_USER_NOT_FOUND_IN_SYSTEM";

    private static final long serialVersionUID = 1L;

    private int code;

    private String error;

    private String message;

    private HttpStatus httpStatus;

    @JsonIgnore
    private boolean isPrintStackTrace;

    public AuthenticationException() {
    }

    public AuthenticationException(String error, String message) {
        super(message);
        this.error = error;
        this.message = message;
    }

    public AuthenticationException(String error, String message, boolean isPrintStackTrace) {
        super(message);
        this.error = error;
        this.message = message;
        this.isPrintStackTrace = isPrintStackTrace;
    }
}
