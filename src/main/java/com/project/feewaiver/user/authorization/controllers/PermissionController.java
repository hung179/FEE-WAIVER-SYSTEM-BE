package com.project.feewaiver.user.authorization.controllers;

import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.common.response.PageResponse;
import com.project.feewaiver.user.authorization.dtos.requests.PermissionRequest;
import com.project.feewaiver.user.authorization.dtos.responses.PermissionResponse;
import com.project.feewaiver.user.authorization.services.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/permission")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping("/create")
    ApiResponse<String> createPermission(
            @RequestPart("permissionRequest") @Valid PermissionRequest permissionRequest){
        return permissionService.createPermission(permissionRequest);
    }

    @GetMapping("/getAll")
    ApiResponse<PageResponse<PermissionResponse>> getAllPermission(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ){
        return permissionService.getAllPermission(page, size);
    }

    @DeleteMapping("/delete/{id}")
    ApiResponse<String> deletePermission(
            @PathVariable Long id){
        return permissionService.deletePermission(id);
    }
}
