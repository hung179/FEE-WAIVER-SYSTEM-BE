package com.project.feewaiver.support.expense.services;

import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.common.response.PageResponse;
import com.project.feewaiver.support.expense.dtos.requests.ExpenseSupportRequest;
import com.project.feewaiver.support.expense.dtos.responses.ExpenseSupportResponse;

public interface ExpenseSupportService {

    ApiResponse<ExpenseSupportResponse> getExpenseSupport(String name);

    ApiResponse<PageResponse<ExpenseSupportResponse>> getAllExpenseSupport(Integer page, Integer size);

    ApiResponse<String> createExpenseSupport(ExpenseSupportRequest request);

    ApiResponse<String> updateExpenseSupport(ExpenseSupportRequest request, Long id);

    ApiResponse<String> deleteExpenseSupport(Long id);

}
