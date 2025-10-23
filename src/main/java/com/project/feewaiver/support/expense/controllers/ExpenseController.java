package com.project.feewaiver.support.expense.controllers;

import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.common.response.PageResponse;
import com.project.feewaiver.support.expense.dtos.requests.ExpenseSupportRequest;
import com.project.feewaiver.support.expense.dtos.responses.ExpenseSupportResponse;
import com.project.feewaiver.support.expense.services.ExpenseSupportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseSupportService service;

    @GetMapping("/getAll")
    public ApiResponse<PageResponse<ExpenseSupportResponse>> getAllExpense(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size){

        return service.getAllExpenseSupport(page, size);
    }

    @GetMapping("/get")
    public ApiResponse<ExpenseSupportResponse> getExpense(
            @RequestPart String name){
        return service.getExpenseSupport(name);
    }

    @PostMapping("/create")
    public ApiResponse<String> createExpense(
            @RequestPart("user") @Valid ExpenseSupportRequest request){
        return service.createExpenseSupport(request);
    }

    @PatchMapping("update/{id}")
    public ApiResponse<String> updateExpense(
            @RequestPart ExpenseSupportRequest request,
            @PathVariable Long id){

        return service.updateExpenseSupport(request, id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteExpense(
            @PathVariable Long id){
        return service.deleteExpenseSupport(id);
    }

}
