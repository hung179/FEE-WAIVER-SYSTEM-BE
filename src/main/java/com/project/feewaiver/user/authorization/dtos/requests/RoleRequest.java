package com.project.feewaiver.user.authorization.dtos.requests;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {
    @Pattern(
            regexp = "^[A-Z_]{3,50}$",
            message = "Role name must contain only uppercase letters and underscores (e.g., ADMIN, STAFF_MEMBER)."
    )
    private String name;

    @Size(max = 200, message = "Description must not exceed 200 characters.")
    private String description;

    private Set<
            @Pattern(
                    regexp = "^[A-Z_]{3,50}$",
                    message = "Permission name must contain only uppercase letters and underscores (e.g., USER_CREATE, GET_STUDENT)."
            )
                    String
            > permissions;
}