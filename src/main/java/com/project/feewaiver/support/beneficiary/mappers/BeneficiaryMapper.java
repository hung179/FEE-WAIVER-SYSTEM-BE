package com.project.feewaiver.support.beneficiary.mappers;

import com.project.feewaiver.support.beneficiary.dtos.requests.BeneficiaryRequest;
import com.project.feewaiver.support.beneficiary.dtos.responses.BeneficiaryResponse;
import com.project.feewaiver.support.beneficiary.entities.Beneficiary;
import com.project.feewaiver.support.expense.mappers.ExpenseSupportMapper;
import com.project.feewaiver.support.tuition.mappers.TuitionSupportMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {ExpenseSupportMapper.class, TuitionSupportMapper.class})
public interface BeneficiaryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "expenseSupport", ignore = true)
    @Mapping(target = "tuitionSupport", ignore = true)
    Beneficiary toBeneficiary(BeneficiaryRequest request);

    @Mapping(target = "expenseSupportResponse", source = "expenseSupport")
    @Mapping(target = "tuitionSupportResponse", source = "tuitionSupport")
    BeneficiaryResponse toBeneficiaryResponse(Beneficiary expenseSupport);

}
