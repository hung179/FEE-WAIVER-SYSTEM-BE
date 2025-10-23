package com.project.feewaiver.support.beneficiary.dtos.requests;

import com.project.feewaiver.support.expense.dtos.requests.ExpenseSupportRequest;
import com.project.feewaiver.support.dtos.requests.TuitionSupportRequest;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BeneficiaryRequest {

    @Size(max = 100, message = "Beneficiary name must not exceed 100 characters")
    String beneficiaryName;

    String expenseSupportName;

    String tuitionSupportName;

}
