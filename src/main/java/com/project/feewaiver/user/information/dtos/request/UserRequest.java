package com.project.feewaiver.user.information.dtos.request;

import com.project.feewaiver.user.information.enums.AccountStatus;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequest {

    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters long.")
    @Pattern(regexp = "^[a-zA-Z0-9_.-]+$", message = "Username can only contain letters, numbers, and the characters '.', '_', '-'.")
    private String username;

    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one lowercase letter, one uppercase letter, one number, and one special character."
    )
    private String password;

    @Size(min = 2, max = 50, message = "Full name must be between 2 and 50 characters long.")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Full name can only contain letters and spaces.")
    private String fullName;

    @Past(message = "Date of birth must be in the past.")
    private LocalDate dateOfBirth;

    private AccountStatus accountStatus;

    @Pattern(regexp = "^[A-Z_]+$", message = "Role name must be uppercase and may contain underscores (e.g., ADMIN_USER).")
    private String roleName;
}
