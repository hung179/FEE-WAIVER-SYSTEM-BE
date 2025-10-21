package com.project.feewaiver.user.authorization.exceptions;

import com.project.feewaiver.common.exceptions.BusinessException;

public class UnauthenticatedException extends BusinessException {
    public UnauthenticatedException(String message) {
        super(message);
    }
}
