package com.project.feewaiver.user.authorization.mapper;

import com.project.feewaiver.user.authorization.dtos.requests.PermissionRequest;
import com.project.feewaiver.user.authorization.dtos.responses.PermissionResponse;
import com.project.feewaiver.user.authorization.entities.Permission;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PermissionMapper {

    Permission toPermission(PermissionRequest permissionRequest);

    PermissionResponse toPermissionResponse(Permission permission);

}
