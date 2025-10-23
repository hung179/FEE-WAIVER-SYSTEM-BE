package com.project.feewaiver.support.tuition.services;

import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.common.response.PageResponse;
import com.project.feewaiver.support.dtos.responses.TuitionSupportResponse;
import com.project.feewaiver.support.dtos.requests.TuitionSupportRequest;

public interface TuitionSupportService {

    ApiResponse<TuitionSupportResponse> getTuitionSupport(String name);

    ApiResponse<PageResponse<TuitionSupportResponse>> getAllTuitionSupport(Integer page, Integer size);

    ApiResponse<String> createTuitionSupport(TuitionSupportRequest request);

    ApiResponse<String> updateTuitionSupport(TuitionSupportRequest request, Long id);

    ApiResponse<String> deleteTuitionSupport(Long id);

}
