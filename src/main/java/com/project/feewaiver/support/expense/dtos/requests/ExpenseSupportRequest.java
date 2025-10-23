package com.project.feewaiver.support.expense.dtos.requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpenseSupportRequest {

    @Size(max = 100, message = "Tuition support name must not exceed 100 characters")
    String expenseSupportName;

    @DecimalMin(value = "0.0", inclusive = true, message = "Tuition reduction amount must be greater than or equal to 0")
    BigDecimal expenseReductionAmount;


}
