package com.project.feewaiver.user.information.controllers;

import com.project.feewaiver.common.enums.ApiType;
import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.user.authorization.exceptions.RoleNotFoundException;
import com.project.feewaiver.user.information.exceptions.AccountLockedException;
import com.project.feewaiver.user.information.exceptions.UserAlreadyExists;
import com.project.feewaiver.user.information.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<?> handleUserNotFoundException(UserNotFoundException ex){
        return new ApiResponse<>(ex.getMessage(), false, ApiType.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse<?> handleUserAlreadyExistsException(UserAlreadyExists ex){
        return new ApiResponse<>(ex.getMessage(), false, ApiType.CONFLICT);
    }

    @ExceptionHandler(AccountLockedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse<?> handleAccountLockedException(AccountLockedException ex){
        return new ApiResponse<>(ex.getMessage(), false, ApiType.LOCKED);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse<?> handleRoleNotFoundException(RoleNotFoundException ex){
        return new ApiResponse<>(ex.getMessage(), false, ApiType.NOT_FOUND);
    }

}
