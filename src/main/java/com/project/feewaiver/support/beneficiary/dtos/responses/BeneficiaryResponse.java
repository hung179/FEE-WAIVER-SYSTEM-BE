package com.project.feewaiver.support.beneficiary.dtos.responses;

import com.project.feewaiver.support.expense.dtos.responses.ExpenseSupportResponse;
import lombok.Data;
import com.project.feewaiver.support.dtos.responses.TuitionSupportResponse;

import java.math.BigDecimal;

@Data
public class BeneficiaryResponse {

    Long id;

    ExpenseSupportResponse expenseSupportResponse;

    TuitionSupportResponse tuitionSupportResponse;
}
