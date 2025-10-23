package com.project.feewaiver.support.expense.dtos.responses;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpenseSupportResponse {

    String expenseSupportName;

    BigDecimal expenseReductionAmount;


}
