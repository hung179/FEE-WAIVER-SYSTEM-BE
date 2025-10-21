package com.project.feewaiver.user.information.dtos.response;

import com.project.feewaiver.user.information.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

}

