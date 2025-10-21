package com.project.feewaiver.user.information.exceptions;

import com.project.feewaiver.common.exceptions.BusinessException;

public class AccountLockedException extends BusinessException {

    public AccountLockedException (String ma){
        super("Tài khoản đã bị khóa: " + ma);
    }

}
