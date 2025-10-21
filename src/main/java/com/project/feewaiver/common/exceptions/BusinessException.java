package com.project.feewaiver.common.exceptions;

//Exception chung cho tầng nghiệp vụ
public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
