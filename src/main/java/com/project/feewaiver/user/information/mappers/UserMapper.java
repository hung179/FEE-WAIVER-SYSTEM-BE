package com.project.feewaiver.user.information.mappers;

import com.project.feewaiver.user.authorization.mapper.PermissionMapper;
import com.project.feewaiver.user.information.dtos.response.UserResponse;
import com.project.feewaiver.user.information.entities.User;
import com.project.feewaiver.user.information.enums.AccountStatus;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = PermissionMapper.class)
public interface UserMapper {


    @Mapping(target = "role", ignore = true)
    UserResponse toUserResponse(User user);

    @AfterMapping
    default void mapRole(User user, @MappingTarget UserResponse userResponse){
        if(userResponse.getAccountStatus().isEmpty()){
            userResponse.setAccountStatus(AccountStatus.ACTIVE.name());
        }
        if (user.getRole() != null){
            userResponse.setRole(user.getRole().getName());
        }
    }
}
