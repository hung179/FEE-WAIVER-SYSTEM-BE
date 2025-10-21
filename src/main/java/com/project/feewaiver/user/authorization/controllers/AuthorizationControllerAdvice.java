package com.project.feewaiver.user.authorization.controllers;

import com.project.feewaiver.common.enums.ApiType;
import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.user.authorization.exceptions.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthorizationControllerAdvice {

    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity<ApiResponse<?>> handleUnauthenticated(UnauthenticatedException ex) {
        ApiResponse<?> response = ApiResponse.builder()
                .success(false)
                .type(ApiType.UNAUTHENTICATED)
                .data(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneral(Exception ex) {
        ApiResponse<?> response = ApiResponse.builder()
                .success(false)
                .type(ApiType.CONFLICT)
                .data("Lỗi hệ thống: " + ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
