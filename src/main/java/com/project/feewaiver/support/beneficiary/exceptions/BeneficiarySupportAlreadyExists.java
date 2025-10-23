package com.project.feewaiver.support.beneficiary.exceptions;

import com.project.feewaiver.common.exceptions.BusinessException;

public class BeneficiarySupportAlreadyExists extends BusinessException {
    public BeneficiarySupportAlreadyExists(String ma) {
        super("Loại đối tượng hỗ trợ "+ma+ " đã tồn tại.");
    }
}
