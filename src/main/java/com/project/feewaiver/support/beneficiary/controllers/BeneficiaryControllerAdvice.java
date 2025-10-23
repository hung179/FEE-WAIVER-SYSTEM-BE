package com.project.feewaiver.support.beneficiary.controllers;

import com.project.feewaiver.common.enums.ApiType;
import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.support.beneficiary.exceptions.BeneficiarySupportAlreadyExists;
import com.project.feewaiver.support.beneficiary.exceptions.BeneficiarySupportNotFoundException;
import com.project.feewaiver.support.expense.exceptions.ExpenseSupportNotFoundException;
import com.project.feewaiver.support.tuition.exceptions.TuitionSupportNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BeneficiaryControllerAdvice {

    @ExceptionHandler(BeneficiarySupportNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<?> handleBeneficiaryNotFoundException(BeneficiarySupportNotFoundException ex){
        return new ApiResponse<>(ex.getMessage(), false, ApiType.NOT_FOUND);
    }

    @ExceptionHandler(BeneficiarySupportAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse<?> handleBeneficiaryAlreadyExistsException(BeneficiarySupportAlreadyExists ex){
        return new ApiResponse<>(ex.getMessage(), false, ApiType.CONFLICT);
    }

    @ExceptionHandler(TuitionSupportNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<?> handleTuitionNotFoundException(TuitionSupportNotFoundException ex){
        return new ApiResponse<>(ex.getMessage(), false, ApiType.NOT_FOUND);
    }

    @ExceptionHandler(ExpenseSupportNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<?> handleExpenseNotFoundException(ExpenseSupportNotFoundException ex){
        return new ApiResponse<>(ex.getMessage(), false, ApiType.NOT_FOUND);
    }

}
