package com.project.feewaiver.user.authorization.controllers;

import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.common.response.PageResponse;
import com.project.feewaiver.user.authorization.dtos.requests.RoleRequest;
import com.project.feewaiver.user.authorization.dtos.responses.RoleResponse;
import com.project.feewaiver.user.authorization.services.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/create")
    ApiResponse<String> createRole(
            @RequestPart("roleRequest") @Valid RoleRequest request){
        return roleService.createRole(request);
    }

    @GetMapping("/getAll")
    ApiResponse<PageResponse<RoleResponse>> getAllRole(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ){
        return roleService.getAllRole(page, size);
    }

    @DeleteMapping("/delete/{id}")
    ApiResponse<String> deleteRole(
            @PathVariable Long id){
        return roleService.deleteRole(id);
    }

}
