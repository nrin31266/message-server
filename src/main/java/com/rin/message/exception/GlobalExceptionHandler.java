package com.rin.message.exception;

import com.rin.message.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException e) {
        return ResponseEntity.status(e.getErrorCode().getStatusCode()).body(ApiResponse.builder()
                        .code(e.getErrorCode().getCode())
                        .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;
        return ResponseEntity.status(errorCode.getStatusCode()).body(ApiResponse.builder()
                .code(errorCode.getCode())
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e.getFieldError().getDefaultMessage());
        return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.builder()
                .code(3000)
                .message(e.getFieldError().getDefaultMessage())
                .build());
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    ResponseEntity<ApiResponse> handleAuthorizationDeniedException(AuthorizationDeniedException e) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        return ResponseEntity.status(errorCode.getStatusCode()).body(ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build());
    }
}
