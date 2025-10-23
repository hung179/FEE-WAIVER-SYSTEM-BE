package com.project.feewaiver.support.expense.exceptions;

import com.project.feewaiver.common.exceptions.BusinessException;

public class ExpenseSupportNotFoundException extends BusinessException {

    public ExpenseSupportNotFoundException(String ma){
        super("Không tìm thấy loại hỗ trợ  " + ma);
    }

}
