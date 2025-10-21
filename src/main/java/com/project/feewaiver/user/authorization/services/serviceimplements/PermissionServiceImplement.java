package com.project.feewaiver.user.authorization.services.serviceimplements;

import com.project.feewaiver.common.enums.ApiType;
import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.common.response.PageResponse;
import com.project.feewaiver.user.authorization.dtos.requests.PermissionRequest;
import com.project.feewaiver.user.authorization.dtos.responses.PermissionResponse;
import com.project.feewaiver.user.authorization.entities.Permission;
import com.project.feewaiver.user.authorization.mapper.PermissionMapper;
import com.project.feewaiver.user.authorization.repositories.PermissionRepository;
import com.project.feewaiver.user.authorization.services.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PermissionServiceImplement implements PermissionService {

    private final PermissionRepository permissionRepository;

    private final PermissionMapper permissionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasAuthority('USER_CREATE')")
    public ApiResponse<String> createPermission(PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);
        permissionRepository.save(permission);
        return ApiResponse.<String>builder()
                .data("Tạo quyền hạn mới thành công")
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('USER_VIEW')")
    public ApiResponse<PageResponse<PermissionResponse>> getAllPermission(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page -1, size);
        Page<Permission> permissions = permissionRepository.findAll(pageable);

        Page<PermissionResponse> permissionResponses = permissions.map(permissionMapper::toPermissionResponse);

        return ApiResponse.<PageResponse<PermissionResponse>>builder()
                .data(PageResponse.<PermissionResponse>builder()
                        .totalElement(permissionResponses.getTotalElements())
                        .data(permissionResponses.getContent())
                        .pageSize(permissionResponses.getSize())
                        .currentPage(permissionResponses.getNumber())
                        .build())
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

    @Override
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public ApiResponse<String> deletePermission(Long id) {
        permissionRepository.deleteById(id);

        return ApiResponse.<String>builder()
                .data("Xóa quyền hạn thành công")
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

}
