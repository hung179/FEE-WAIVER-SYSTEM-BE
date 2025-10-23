package com.project.feewaiver.support.beneficiary.services.expenseserviceimplement;

import com.project.feewaiver.common.enums.ApiType;
import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.common.response.PageResponse;
import com.project.feewaiver.support.beneficiary.dtos.requests.BeneficiaryRequest;
import com.project.feewaiver.support.beneficiary.dtos.responses.BeneficiaryResponse;
import com.project.feewaiver.support.beneficiary.entities.Beneficiary;
import com.project.feewaiver.support.beneficiary.exceptions.BeneficiarySupportAlreadyExists;
import com.project.feewaiver.support.beneficiary.exceptions.BeneficiarySupportNotFoundException;
import com.project.feewaiver.support.beneficiary.mappers.BeneficiaryMapper;
import com.project.feewaiver.support.beneficiary.repositoires.BeneficiaryRepository;
import com.project.feewaiver.support.beneficiary.services.BeneficiaryService;
import com.project.feewaiver.support.expense.exceptions.ExpenseSupportNotFoundException;
import com.project.feewaiver.support.expense.repositoires.ExpenseRepository;
import com.project.feewaiver.support.tuition.exceptions.TuitionSupportNotFoundException;
import com.project.feewaiver.support.tuition.repositoires.TuitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BeneficiaryServiceImplement implements BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;

    private final BeneficiaryMapper beneficiaryMapper;

    private final ExpenseRepository expenseRepository;

    private final TuitionRepository tuitionRepository;

    @Override
    public ApiResponse<BeneficiaryResponse> getBeneficiary(Long id){

        BeneficiaryResponse response = beneficiaryMapper.toBeneficiaryResponse(beneficiaryRepository.findById(id).orElseThrow(
                ()-> new BeneficiarySupportNotFoundException(id)
        ));
        return ApiResponse.<BeneficiaryResponse>builder()
                .data(response)
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

    @Override
    public ApiResponse<PageResponse<BeneficiaryResponse>> getAllBeneficiary(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page -1, size);
        Page<BeneficiaryResponse> responses = beneficiaryRepository.findAll(pageable) .map(beneficiaryMapper::toBeneficiaryResponse);

        PageResponse<BeneficiaryResponse> pageResponse = PageResponse.<BeneficiaryResponse>builder()
                .totalElement(responses.getTotalElements())
                .data(responses.getContent())
                .pageSize(responses.getSize())
                .currentPage(responses.getNumber())
                .build();

        return ApiResponse.<PageResponse<BeneficiaryResponse>>builder()
                .data(pageResponse)
                .success(true)
                .build();
    }

    @Override
    public ApiResponse<String> createBeneficiary(BeneficiaryRequest request){

        if (beneficiaryRepository.existsByExpenseSupport_ExpenseSupportNameAndTuitionSupport_TuitionSupportName(request.getExpenseSupportName(), request.getTuitionSupportName())){
            throw new BeneficiarySupportAlreadyExists(request.getExpenseSupportName() + "và" + request.getTuitionSupportName());
        }



            Beneficiary beneficiary = beneficiaryMapper.toBeneficiary(request);



            beneficiary.toBuilder()
                    .expenseSupport(
                            expenseRepository.findByExpenseSupportName(request.getExpenseSupportName()).orElseThrow(
                                    () -> new ExpenseSupportNotFoundException(request.getExpenseSupportName())
                            )
                    )
                    .tuitionSupport(
                            tuitionRepository.findByTuitionSupportName(request.getTuitionSupportName()).orElseThrow(
                                    () -> new TuitionSupportNotFoundException(request.getTuitionSupportName())
                            )
                    )
                    .build();

            beneficiaryRepository.save(beneficiary);

            return ApiResponse.<String>builder()
                    .data("Thêm thêm đối tượng hỗ trợ thành công.")
                    .success(true)
                    .type(ApiType.SUCCESS)
                    .build();
    }

    @Override
    public ApiResponse<String> deleteBeneficiary(Long id){

        beneficiaryRepository.deleteById(id);

        return ApiResponse.<String>builder()
                .data("Xóa đối tượng hỗ trợ thành công")
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

}
