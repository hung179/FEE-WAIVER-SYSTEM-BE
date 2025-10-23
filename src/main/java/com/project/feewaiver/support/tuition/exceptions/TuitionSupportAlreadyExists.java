package com.project.feewaiver.support.tuition.exceptions;

import com.project.feewaiver.common.exceptions.BusinessException;

public class TuitionSupportAlreadyExists extends BusinessException {
    public TuitionSupportAlreadyExists(String ma) {
        super("Loại hỗ trợ "+ma+ " đã tồn tại.");
    }
}
