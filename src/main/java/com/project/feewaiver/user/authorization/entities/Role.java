package com.project.feewaiver.user.authorization.entities;

import com.project.feewaiver.common.abstractentity.AbstractEntity;
import com.project.feewaiver.user.information.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Set;


@Entity(name = "Role")
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @NotBlank(message = "Role name is required.")
    @Column(name = "name", nullable = false, unique = true)
    String name;

    @NotBlank(message = "Role description is required.")
    @Column(name = "description")
    String description;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<User> users;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<RoleHasPermission> roleHasPermissions;

}
