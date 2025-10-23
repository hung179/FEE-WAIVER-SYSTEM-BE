package com.project.feewaiver.support.expense.mappers;

import com.project.feewaiver.support.expense.dtos.requests.ExpenseSupportRequest;
import com.project.feewaiver.support.expense.dtos.responses.ExpenseSupportResponse;
import com.project.feewaiver.support.expense.entities.ExpenseSupport;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ExpenseSupportMapper {

    @Mapping(target = "id", ignore = true)
    ExpenseSupport toExpenseSupport(ExpenseSupportRequest request);

    ExpenseSupportResponse toExpenseSupportResponse(ExpenseSupport expenseSupport);

}
