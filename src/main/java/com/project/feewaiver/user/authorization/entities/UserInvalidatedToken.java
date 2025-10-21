package com.project.feewaiver.user.authorization.entities;

import com.project.feewaiver.common.abstractentity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Token")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInvalidatedToken extends AbstractEntity {

    @Id
    @NotBlank(message = "Invalidated token id is required.")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @NotNull(message = "Expiry time of invalidated token is required.")
    @Column(name = "expiry_time", nullable = false)
    private Date expiryTime;
}
