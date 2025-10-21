package com.project.feewaiver.user.authorization.dtos.requests;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionRequest {

    @Pattern(
            regexp = "^[A-Z_]{3,50}$",
            message = "Permission name must contain only uppercase letters and underscores (e.g., USER_CREATE)."
    )
    private String name;

    @Size(max = 200, message = "Description must not exceed 200 characters.")
    private String description;

}