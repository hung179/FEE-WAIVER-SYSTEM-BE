package com.project.feewaiver.user.authorization.entities;

import com.project.feewaiver.common.abstractentity.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity(name = "Permission")
@Table(name = "permission")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    @NotBlank(message = "Permission name is required.")
    String name;

    @Column(name = "description")
    @NotBlank(message = "Permission description is required.")
    String description;

    @OneToMany(mappedBy = "permission")
    Set<RoleHasPermission> roleHasPermissions;

}
