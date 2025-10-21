package com.project.feewaiver.user.authorization.exceptions;

import com.project.feewaiver.common.exceptions.BusinessException;

public class RoleNotFoundException extends BusinessException {
    public RoleNotFoundException(String message) {
        super( "Không tìm thấy vai trò: "+ message);
    }
}
