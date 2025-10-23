package com.project.feewaiver.user.information.mappers;

import com.project.feewaiver.manage.organization.dtos.response.AddressResponse;
import com.project.feewaiver.manage.organization.dtos.response.OrganizationResponse;
import com.project.feewaiver.user.authorization.mapper.PermissionMapper;
import com.project.feewaiver.user.information.dtos.response.UserResponse;
import com.project.feewaiver.user.information.entities.User;
import com.project.feewaiver.user.information.enums.AccountStatus;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = PermissionMapper.class)
public interface UserMapper {


    @Mapping(target = "role", ignore = true)
    @Mapping(target = "organizations", ignore = true)
    UserResponse toUserResponse(User user);

    @AfterMapping
    default void mapRole(User user, @MappingTarget UserResponse userResponse){
        if(userResponse.getAccountStatus().isEmpty()){
            userResponse.setAccountStatus(AccountStatus.ACTIVE.name());
        }
        if (user.getRole() != null){
            userResponse.setRole(user.getRole().getName());
        }

        if (!user.getUserDetails().isEmpty()) {
            Set<OrganizationResponse> organizationResponses = user.getUserDetails().stream()
                    .map(or -> OrganizationResponse.builder()
                            .organizationName(or.getOrganization().getOrganizationName())
                            .managementLevel(or.getOrganization().getManagementLevel().name())
                            .phoneNumber(or.getOrganization().getPhoneNumber())
                            .email(or.getOrganization().getOrganizationName())
                            .addresses(AddressResponse.builder()
                                            .province(or.getOrganization().getAddress().getProvince())
                                            .commune(or.getOrganization().getAddress().getCommune())
                                            .detailedAddress(or.getOrganization().getAddress().getDetailedAddress())
                                            .build()
                            )
                            .build()).collect(Collectors.toSet());
        }
    }
}
