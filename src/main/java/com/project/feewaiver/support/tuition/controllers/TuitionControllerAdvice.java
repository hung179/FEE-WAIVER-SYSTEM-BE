package com.project.feewaiver.support.tuition.controllers;

import com.project.feewaiver.common.enums.ApiType;
import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.support.tuition.exceptions.TuitionSupportAlreadyExists;
import com.project.feewaiver.support.tuition.exceptions.TuitionSupportNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TuitionControllerAdvice {

    @ExceptionHandler(TuitionSupportNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<?> handleTuitionNotFoundException(TuitionSupportNotFoundException ex){
        return new ApiResponse<>(ex.getMessage(), false, ApiType.NOT_FOUND);
    }

    @ExceptionHandler(TuitionSupportAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse<?> handleTuitionAlreadyExistsException(TuitionSupportAlreadyExists ex){
        return new ApiResponse<>(ex.getMessage(), false, ApiType.CONFLICT);
    }

}
