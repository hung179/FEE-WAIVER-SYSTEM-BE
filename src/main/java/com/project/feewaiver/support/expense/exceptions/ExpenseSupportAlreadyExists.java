package com.project.feewaiver.support.expense.exceptions;

import com.project.feewaiver.common.exceptions.BusinessException;

public class ExpenseSupportAlreadyExists extends BusinessException {
    public ExpenseSupportAlreadyExists(String ma) {
        super("Loại hỗ trợ "+ma+ " đã tồn tại.");
    }
}
