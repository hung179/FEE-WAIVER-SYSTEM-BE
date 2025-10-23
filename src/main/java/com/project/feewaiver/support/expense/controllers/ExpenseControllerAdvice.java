package com.project.feewaiver.support.expense.controllers;

import com.project.feewaiver.common.enums.ApiType;
import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.support.expense.exceptions.ExpenseSupportAlreadyExists;
import com.project.feewaiver.support.expense.exceptions.ExpenseSupportNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExpenseControllerAdvice {

    @ExceptionHandler(ExpenseSupportNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<?> handleExpenseNotFoundException(ExpenseSupportNotFoundException ex){
        return new ApiResponse<>(ex.getMessage(), false, ApiType.NOT_FOUND);
    }

    @ExceptionHandler(ExpenseSupportAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse<?> handleExpenseAlreadyExistsException(ExpenseSupportAlreadyExists ex){
        return new ApiResponse<>(ex.getMessage(), false, ApiType.CONFLICT);
    }

}
