package com.project.feewaiver.user.information.exceptions;

import com.project.feewaiver.common.exceptions.BusinessException;

public class UserAlreadyExists extends BusinessException {
    public UserAlreadyExists(String ma) {
        super("Người dùng với mã "+ma+ " đã tồn tại.");
    }
}
