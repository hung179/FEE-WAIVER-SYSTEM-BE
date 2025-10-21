package com.project.feewaiver.user.authorization.services;

import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.common.response.PageResponse;
import com.project.feewaiver.user.authorization.dtos.requests.RoleRequest;
import com.project.feewaiver.user.authorization.dtos.responses.RoleResponse;

public interface RoleService {

    ApiResponse<String> createRole(RoleRequest request);

    ApiResponse<PageResponse<RoleResponse>> getAllRole(Integer page, Integer size);

    ApiResponse<String> deleteRole(Long id);

}
