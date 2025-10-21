package com.project.feewaiver.user.authorization.services.serviceimplements;

import com.project.feewaiver.common.enums.ApiType;
import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.common.response.PageResponse;
import com.project.feewaiver.user.authorization.dtos.requests.RoleRequest;
import com.project.feewaiver.user.authorization.dtos.responses.RoleResponse;
import com.project.feewaiver.user.authorization.entities.Permission;
import com.project.feewaiver.user.authorization.entities.Role;
import com.project.feewaiver.user.authorization.entities.RoleHasPermission;
import com.project.feewaiver.user.authorization.mapper.RoleMapper;
import com.project.feewaiver.user.authorization.repositories.PermissionRepository;
import com.project.feewaiver.user.authorization.repositories.RoleRepository;
import com.project.feewaiver.user.authorization.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImplement implements RoleService {

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    private final RoleMapper roleMapper;

    @Override
    @PreAuthorize("hasAuthority('USER_CREATE')")
    public ApiResponse<String> createRole(RoleRequest request){
        Role role = roleMapper.toRole(request);

        List<Permission> permissions = permissionRepository.findAllByNameIn(request.getPermissions());

        Set<RoleHasPermission> mappings = permissions.stream()
                        .map(p -> RoleHasPermission.builder()
                                .role(role)
                                .permission(p)
                                .build())
                                .collect(Collectors.toSet());

        role.setRoleHasPermissions(mappings);
        roleRepository.save(role);
        return ApiResponse.<String>builder()
                .data("Khởi tạo vai trò thành công")
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('USER_VIEW')")
    public ApiResponse<PageResponse<RoleResponse>> getAllRole(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page -1, size);
        Page<Role> roles = roleRepository.findAll(pageable);

        Page<RoleResponse> roleResponses = roles.map(roleMapper::toRoleResponse);

        return ApiResponse.<PageResponse<RoleResponse>>builder()
                .data(PageResponse.<RoleResponse>builder()
                        .totalElement(roleResponses.getTotalElements())
                        .data(roleResponses.getContent())
                        .pageSize(roleResponses.getSize())
                        .currentPage(roleResponses.getNumber())
                        .build())
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

    @Override
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public ApiResponse<String> deleteRole(Long id) {
        roleRepository.deleteById(id);

        return ApiResponse.<String>builder()
                .data("Xóa vai trò thành công")
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }
}
