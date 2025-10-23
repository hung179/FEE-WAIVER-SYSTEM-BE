package com.project.feewaiver.support.beneficiary.exceptions;

import com.project.feewaiver.common.exceptions.BusinessException;

public class BeneficiarySupportNotFoundException extends BusinessException {

    public BeneficiarySupportNotFoundException(Long ma){
        super("Không tìm thấy đối tượng hỗ trợ " + ma);
    }

}
