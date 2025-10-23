package com.project.feewaiver.support.tuition.controllers;

import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.common.response.PageResponse;
import com.project.feewaiver.support.tuition.services.TuitionSupportService;
import com.project.feewaiver.support.dtos.responses.TuitionSupportResponse;
import com.project.feewaiver.support.dtos.requests.TuitionSupportRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tuition")
@RequiredArgsConstructor
public class TuitionController {

    private final TuitionSupportService service;

    @GetMapping("/getAll")
    public ApiResponse<PageResponse<TuitionSupportResponse>> getAllTuition(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size){

        return service.getAllTuitionSupport(page, size);
    }

    @GetMapping("/get")
    public ApiResponse<TuitionSupportResponse> getTuition(
            @RequestPart String name){
        return service.getTuitionSupport(name);
    }

    @PostMapping("/create")
    public ApiResponse<String> createTuition(
            @RequestPart("user") @Valid TuitionSupportRequest request){
        return service.createTuitionSupport(request);
    }

    @PatchMapping("update/{id}")
    public ApiResponse<String> updateTuition(
            @RequestPart TuitionSupportRequest request,
            @PathVariable Long id){
        return service.updateTuitionSupport(request, id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteTuition(
            @PathVariable Long id){
        return service.deleteTuitionSupport(id);
    }

}
