package com.project.feewaiver.support.beneficiary.controllers;

import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.common.response.PageResponse;
import com.project.feewaiver.support.beneficiary.dtos.requests.BeneficiaryRequest;
import com.project.feewaiver.support.beneficiary.dtos.responses.BeneficiaryResponse;
import com.project.feewaiver.support.beneficiary.services.BeneficiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("expense")
@RequiredArgsConstructor
public class BeneficiaryController {

    private final BeneficiaryService service;

    @GetMapping("/getAll")
    public ApiResponse<PageResponse<BeneficiaryResponse>> getAllBeneficiary(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size){

        return service.getAllBeneficiary(page, size);
    }

    @GetMapping("/get/{id}")
    public ApiResponse<BeneficiaryResponse> getBeneficiary(
            @RequestPart Long id){
        return service.getBeneficiary(id);
    }

    @PostMapping("/create")
    public ApiResponse<String> createBeneficiary(
            @RequestPart("user") @Valid BeneficiaryRequest request){
        return service.createBeneficiary(request);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteBeneficiary(
            @PathVariable Long id){
        return service.deleteBeneficiary(id);
    }

}
