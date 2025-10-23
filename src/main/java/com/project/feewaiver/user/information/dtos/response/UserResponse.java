package com.project.feewaiver.user.information.dtos.response;

import com.project.feewaiver.manage.organization.dtos.response.OrganizationResponse;
import com.project.feewaiver.user.information.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String username;

    private String fullName;

    private LocalDate dateOfBirth;

    private String accountStatus;

    private String role;

    private Set<OrganizationResponse> organizations;

}

