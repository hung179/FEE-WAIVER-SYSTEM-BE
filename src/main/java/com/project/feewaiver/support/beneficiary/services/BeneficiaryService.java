package com.project.feewaiver.support.beneficiary.services;

import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.common.response.PageResponse;
import com.project.feewaiver.support.beneficiary.dtos.requests.BeneficiaryRequest;
import com.project.feewaiver.support.beneficiary.dtos.responses.BeneficiaryResponse;

public interface BeneficiaryService {

    ApiResponse<BeneficiaryResponse> getBeneficiary(Long id);

    ApiResponse<PageResponse<BeneficiaryResponse>> getAllBeneficiary(Integer page, Integer size);

    ApiResponse<String> createBeneficiary(BeneficiaryRequest request);

    ApiResponse<String> deleteBeneficiary(Long id);

}
