package com.project.feewaiver.support.expense.services.expenseserviceimplement;

import com.project.feewaiver.common.enums.ApiType;
import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.common.response.PageResponse;
import com.project.feewaiver.support.expense.dtos.requests.ExpenseSupportRequest;
import com.project.feewaiver.support.expense.dtos.responses.ExpenseSupportResponse;
import com.project.feewaiver.support.expense.entities.ExpenseSupport;
import com.project.feewaiver.support.expense.exceptions.ExpenseSupportAlreadyExists;
import com.project.feewaiver.support.expense.exceptions.ExpenseSupportNotFoundException;
import com.project.feewaiver.support.expense.mappers.ExpenseSupportMapper;
import com.project.feewaiver.support.expense.repositoires.ExpenseRepository;
import com.project.feewaiver.support.expense.services.ExpenseSupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseSupportServiceImplement implements ExpenseSupportService {

    private final ExpenseRepository expenseRepository;

    private final ExpenseSupportMapper expenseSupportMapper;

    @Override
    public ApiResponse<ExpenseSupportResponse> getExpenseSupport(String name){

        ExpenseSupportResponse response = expenseSupportMapper.toExpenseSupportResponse(expenseRepository.findByExpenseSupportName(name).orElseThrow(
                ()-> new ExpenseSupportNotFoundException(name)
        ));
        return ApiResponse.<ExpenseSupportResponse>builder()
                .data(response)
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

    @Override
    public ApiResponse<PageResponse<ExpenseSupportResponse>> getAllExpenseSupport(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page -1, size);
        Page<ExpenseSupportResponse> responses = expenseRepository.findAll(pageable) .map(expenseSupportMapper::toExpenseSupportResponse);

        PageResponse<ExpenseSupportResponse> pageResponse = PageResponse.<ExpenseSupportResponse>builder()
                .totalElement(responses.getTotalElements())
                .data(responses.getContent())
                .pageSize(responses.getSize())
                .currentPage(responses.getNumber())
                .build();

        return ApiResponse.<PageResponse<ExpenseSupportResponse>>builder()
                .data(pageResponse)
                .success(true)
                .build();
    }

    @Override
    public ApiResponse<String> createExpenseSupport(ExpenseSupportRequest request){
        if (expenseRepository.existsByExpenseSupportName(request.getExpenseSupportName())){
            throw new ExpenseSupportAlreadyExists(request.getExpenseSupportName());
        }

            ExpenseSupport expenseSupport = expenseSupportMapper.toExpenseSupport(request);

            expenseRepository.save(expenseSupport);

            return ApiResponse.<String>builder()
                    .data("Thêm loại hỗ trợ chi phí thành công.")
                    .success(true)
                    .type(ApiType.SUCCESS)
                    .build();
    }

    @Override
    public ApiResponse<String> updateExpenseSupport(ExpenseSupportRequest request, Long id){
        ExpenseSupport expenseSupport = expenseRepository.findById(id).orElseThrow(
                ()-> new ExpenseSupportNotFoundException(String.valueOf(id))
        );

        Optional.ofNullable(request.getExpenseReductionAmount()).ifPresent(expenseSupport::setExpenseReductionAmount);

        expenseRepository.save(expenseSupport);
        return ApiResponse.<String>builder()
                .data("Cập nhật thông tin hỗ trợ chi phí thành công.")
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

    @Override
    public ApiResponse<String> deleteExpenseSupport(Long id){

        expenseRepository.deleteById(id);

        return ApiResponse.<String>builder()
                .data("Xóa loại hỗ trợ chi phí thành công")
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

}
