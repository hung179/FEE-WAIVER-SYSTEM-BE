package com.project.feewaiver.user.authorization.services;

import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.common.response.PageResponse;
import com.project.feewaiver.user.authorization.dtos.requests.PermissionRequest;
import com.project.feewaiver.user.authorization.dtos.responses.PermissionResponse;

public interface PermissionService {

     ApiResponse<String> createPermission(PermissionRequest request);

     ApiResponse<PageResponse<PermissionResponse>> getAllPermission(Integer page, Integer size);

     ApiResponse<String> deletePermission(Long id);
}
