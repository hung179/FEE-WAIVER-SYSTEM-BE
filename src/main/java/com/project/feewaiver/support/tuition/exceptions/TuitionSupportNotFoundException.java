package com.project.feewaiver.support.tuition.exceptions;

import com.project.feewaiver.common.exceptions.BusinessException;

public class TuitionSupportNotFoundException extends BusinessException {

    public TuitionSupportNotFoundException(String ma){
        super("Không tìm thấy loại hỗ trợ  " + ma);
    }

}
