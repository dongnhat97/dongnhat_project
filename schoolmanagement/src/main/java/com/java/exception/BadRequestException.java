package com.java.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * Badrequest Exception
 *
 */
@Getter
@Setter
public class BadRequestException extends Exception {

    public static final String ERROR_INVALID_TOKEN = "ERROR_INVALID_TOKEN";

    public static final String EMAIL_IS_INVALID = "EMAIL_IS_INVALID";

    public static final String ERROR_SIGN_IN_BAD_REQUEST = "ERROR_SIGN_IN_BAD_REQUEST";

    public static final String ERROR_USER_HAS_BEEN_DELETED = "ERROR_USER_HAS_BEEN_DELETED";

    public static final String ERROR_USER_NOT_ACTIVATED = "ERROR_USER_NOT_ACTIVATED";

    public static final String ERROR_AUTHENTICATION_FAILED_WRONG_EMAIL_OR_PASSWORD = "ERROR_AUTHENTICATION_FAILED_WRONG_EMAIL_OR_PASSWORD";

    public static final String ERROR_NOTIFICATION_HAS_BEEN_DELETED = "ERROR_NOTIFICATION_HAS_BEEN_DELETED";

    private static final long serialVersionUID = 1L;

    private String error;

    private String message;

    private boolean isJson;

    @JsonIgnore
    private boolean isPrintStackTrace;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message, boolean isJson) {
        super(message);
        this.message = message;
        this.isJson = isJson;
    }

    public BadRequestException(String error) {
        this.error = error;
    }

    public BadRequestException(String error_code, String message) {
        super(message);
        this.error = error_code;
        this.message = message;
        this.isJson = true;
    }

    public BadRequestException(String error, String message, boolean isJson) {
        super(message);
        this.error = error;
        this.message = message;
        this.isJson = isJson;
    }
}
