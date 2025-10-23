package com.project.feewaiver.support.tuition.services.tuitionserviceimplement;

import com.project.feewaiver.common.enums.ApiType;
import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.common.response.PageResponse;
import com.project.feewaiver.support.expense.dtos.requests.ExpenseSupportRequest;
import com.project.feewaiver.support.expense.dtos.responses.ExpenseSupportResponse;
import com.project.feewaiver.support.expense.entities.ExpenseSupport;
import com.project.feewaiver.support.expense.mappers.ExpenseSupportMapper;
import com.project.feewaiver.support.expense.repositoires.ExpenseRepository;
import com.project.feewaiver.support.tuition.entities.TuitionSupport;
import com.project.feewaiver.support.tuition.exceptions.TuitionSupportAlreadyExists;
import com.project.feewaiver.support.tuition.exceptions.TuitionSupportNotFoundException;
import com.project.feewaiver.support.tuition.mappers.TuitionSupportMapper;
import com.project.feewaiver.support.tuition.repositoires.TuitionRepository;
import com.project.feewaiver.support.tuition.services.TuitionSupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.feewaiver.support.dtos.responses.TuitionSupportResponse;
import com.project.feewaiver.support.dtos.requests.TuitionSupportRequest;


import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TuitionSupportServiceImplement implements TuitionSupportService {

    private final TuitionRepository tuitionRepository;

    private final TuitionSupportMapper tuitionSupportMapper;

    @Override
    public ApiResponse<TuitionSupportResponse> getTuitionSupport(String name){

        TuitionSupportResponse response = tuitionSupportMapper.toTuitionSupportResponse(tuitionRepository.findByTuitionSupportName(name).orElseThrow(
                () -> new TuitionSupportNotFoundException(name)
        ));
        return ApiResponse.<TuitionSupportResponse>builder()
                .data(response)
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

    @Override
    public ApiResponse<PageResponse<TuitionSupportResponse>> getAllTuitionSupport(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page -1, size);
        Page<TuitionSupportResponse> responses = tuitionRepository.findAll(pageable) .map(tuitionSupportMapper::toTuitionSupportResponse);

        PageResponse<TuitionSupportResponse> pageResponse = PageResponse.<TuitionSupportResponse>builder()
                .totalElement(responses.getTotalElements())
                .data(responses.getContent())
                .pageSize(responses.getSize())
                .currentPage(responses.getNumber())
                .build();

        return ApiResponse.<PageResponse<TuitionSupportResponse>>builder()
                .data(pageResponse)
                .success(true)
                .build();
    }

    @Override
    public ApiResponse<String> createTuitionSupport(TuitionSupportRequest request){
        if (tuitionRepository.existsByTuitionSupportName(request.getTuitionSupportName())){
            throw new TuitionSupportAlreadyExists(request.getTuitionSupportName());
        }

        TuitionSupport tuitionSupport = tuitionSupportMapper.toTuitionSupport(request);

        tuitionRepository.save(tuitionSupport);

            return ApiResponse.<String>builder()
                    .data("Thêm loại hỗ trợ học phí thành công.")
                    .success(true)
                    .type(ApiType.SUCCESS)
                    .build();
    }

    @Override
    public ApiResponse<String> updateTuitionSupport(TuitionSupportRequest request, Long id){
        TuitionSupport tuitionSupport = tuitionRepository.findById(id).orElseThrow(
                () -> new TuitionSupportNotFoundException(request.getTuitionSupportName())
        );

        Optional.ofNullable(request.getTuitionReductionAmount()).ifPresent(tuitionSupport::setTuitionReductionAmount);
        Optional.ofNullable(request.getTuitionReductionRate()).ifPresent(tuitionSupport::setTuitionReductionAmount);

        tuitionRepository.save(tuitionSupport);
        return ApiResponse.<String>builder()
                .data("Cập nhật thông tin hỗ trợ học phí thành công.")
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

    @Override
    public ApiResponse<String> deleteTuitionSupport(Long id){

        tuitionRepository.deleteById(id);

        return ApiResponse.<String>builder()
                .data("Xóa loại hỗ trợ học phí thành công")
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

}
