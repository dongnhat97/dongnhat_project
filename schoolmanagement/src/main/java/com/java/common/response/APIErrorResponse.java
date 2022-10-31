package com.java.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public class APIErrorResponse<T> extends ResponseEntity {

    public APIErrorResponse(T body, HttpStatus status) {
        this(APIErrorResponse.APIBody.builder().data(body).build(), null, status);
    }

    public APIErrorResponse(int code, T body) {
        this(APIErrorResponse.APIBody.builder().data(body).build(), null, HttpStatus.OK);
    }

    public APIErrorResponse(int code, T body, HttpStatus httpStatus) {
        this(APIErrorResponse.APIBody.builder().data(body).build(), null, httpStatus);
    }

    public APIErrorResponse(String code, T body, Map<String, String> error, HttpStatus httpStatus) {
        this(APIErrorResponse.APIBody.builder().data(body).error(error).status(code).build(), null, httpStatus);
    }

    public APIErrorResponse(APIErrorResponse.APIBody body, MultiValueMap<String, String> headers, HttpStatus httpStatus) {
        super(body, headers, httpStatus);
    }

    /**
     * Created APIResponse
     *
     * @param status
     * @param body
     * @param error
     * @param httpStatus
     * @param <T>
     * @return APIErrorResponse
     */
    public static <T> APIErrorResponse<T> createdStatus(String status, T body, Map<String, String> error, HttpStatus httpStatus) {
        return new APIErrorResponse<T>(status, body, error, httpStatus);
    }

    /**
     * Error APIResponse
     *
     * @param status
     * @param body
     * @param error
     * @param httpStatus
     * @param <T>
     * @return APIErrorResponse
     */
    public static <T> APIErrorResponse<T> errorStatus(String status, T body, Map<String, String> error, HttpStatus httpStatus) {
        return new APIErrorResponse<T>(status, body, error, httpStatus);
    }

    @Getter
    @Setter
    @Builder
    public static class APIBody<T> {
        String status;
        Map<String, String> error;
        T data;
    }
}

