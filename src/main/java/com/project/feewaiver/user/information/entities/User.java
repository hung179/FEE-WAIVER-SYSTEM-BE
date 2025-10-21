package com.project.feewaiver.user.information.entities;

import com.project.feewaiver.common.abstractentity.AbstractEntity;
import com.project.feewaiver.user.authorization.entities.Role;
import com.project.feewaiver.user.information.enums.AccountStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
@SuperBuilder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @NotBlank(message = "Username is required.")
    @Column(name = "username", nullable = false, unique = true)
    String username;

    @NotBlank(message = "Password is required.")
    @Column(name = "password", nullable = false, unique = true)
    String password;

    @NotBlank(message = "User's full name is required.")
    @Column(name = "full_name")
    String fullName;

    @NotNull(message = "User's date of birth is required.")
    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ACTIVE'")
    AccountStatus accountStatus;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @NotNull(message = "User role is required.")
    Role role;

}
