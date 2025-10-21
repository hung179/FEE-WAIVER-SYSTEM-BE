package com.project.feewaiver.user.information.exceptions;

import com.project.feewaiver.common.exceptions.BusinessException;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException(String ma){
        super("Không tìm thấy người dùng " + ma);
    }

}
