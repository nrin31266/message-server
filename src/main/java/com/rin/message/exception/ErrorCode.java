package com.rin.message.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    USER_EXISTS(1001, "User exists, check username and email", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1002, "User not found", HttpStatus.NOT_FOUND),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    WRONG_PASSWORD(1003, "Wrong password", HttpStatus.BAD_REQUEST),
    TOKEN_EXPIRED(1004, "Token expired", HttpStatus.UNAUTHORIZED),
    TOKEN_INVALID(1005, "Token Invalid", HttpStatus.UNAUTHORIZED),
    CAN_NOT_PARSE_TOKEN(1006, "Can't parse token", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1008, "You do not have permission", HttpStatus.FORBIDDEN),
    ;


    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.statusCode=httpStatusCode;
    }


    final int code;
    final String message;
    private final HttpStatusCode statusCode;
}
