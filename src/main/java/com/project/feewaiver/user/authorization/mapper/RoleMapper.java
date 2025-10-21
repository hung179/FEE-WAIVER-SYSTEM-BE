package com.project.feewaiver.user.authorization.mapper;

import com.project.feewaiver.user.authorization.dtos.requests.RoleRequest;
import com.project.feewaiver.user.authorization.dtos.responses.PermissionResponse;
import com.project.feewaiver.user.authorization.dtos.responses.RoleResponse;
import com.project.feewaiver.user.authorization.entities.Role;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface RoleMapper {

    @Mapping(target = "roleHasPermissions", ignore = true)
    @Mapping(target = "id", ignore = true)
    Role toRole(RoleRequest request);

    @Mapping(target = "permissions", ignore = true)
    RoleResponse toRoleResponse(Role role);

    @AfterMapping
    default void mapPermissions(Role role, @MappingTarget RoleResponse roleResponse){
        if(role.getRoleHasPermissions() != null){
            Set<PermissionResponse> permissions = role.getRoleHasPermissions().stream()
                    .map(rp -> PermissionResponse.builder()
                            .name(rp.getPermission().getName())
                            .description(rp.getPermission().getDescription())
                            .build()
                            ).collect(Collectors.toSet());
            roleResponse.setPermissions(permissions);
        }
    }

}
