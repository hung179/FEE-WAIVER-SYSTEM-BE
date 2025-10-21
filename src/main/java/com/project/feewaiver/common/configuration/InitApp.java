package com.project.feewaiver.common.configuration;

import com.project.feewaiver.user.authorization.entities.Permission;
import com.project.feewaiver.user.authorization.entities.Role;
import com.project.feewaiver.user.authorization.entities.RoleHasPermission;
import com.project.feewaiver.user.authorization.exceptions.RoleNotFoundException;
import com.project.feewaiver.user.authorization.repositories.PermissionRepository;
import com.project.feewaiver.user.authorization.repositories.RoleRepository;
import com.project.feewaiver.user.information.repositories.UserRepository;
import com.project.feewaiver.user.information.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
@Slf4j(topic = "INIT-APPLICATION")
public class InitApp {

    private final PasswordEncoder passwordEncoder;

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driver-class-name",
            havingValue = "org.postgresql.Driver"
    )
    ApplicationRunner initApplication(RoleRepository roleRepository, UserRepository userRepository, PermissionRepository permissionRepository) {
        log.info("Initializing application.....");
        return args -> {

            initPermissions(permissionRepository);

            initRoles(roleRepository);

            log.info("Application initialization completed .....");

            assignPermissionsToAdmin(roleRepository, permissionRepository);

            initAdminUser(userRepository, roleRepository);
        };
    }

    private void initPermissions(PermissionRepository repository) {

        List<String> defaultPermissions = List.of(
                "USER_VIEW", "USER_CREATE", "USER_UPDATE", "USER_DELETE",
                "USER_LOCK", "USER_RESET_PASSWORD",

                "CREATE_ORGANIZATION", "UPDATE_ORGANIZATION", "GET_ORGANIZATION", "DELETE_ORGANIZATION",
                "GET_ALL_ORGANIZATION",

                "CREATE_STUDENT", "GET_STUDENT", "GET_ALL_STUDENT", "UPDATE_STUDENT", "DELETE_STUDENT",

                "CREATE_SCHOOL_YEAR", "GET_SCHOOL_YEAR", "GET_ALL_SCHOOL_YEAR", "UPDATE_SCHOOL_YEAR", "DELETE_SCHOOL_YEAR",

                "CREATE_SEMESTER", "GET_SEMESTER", "GET_ALL_SEMESTER", "UPDATE_SEMESTER", "DELETE_SEMESTER"
        );

        for (String perName : defaultPermissions){
            repository.findByName(perName).orElseGet(
                    () -> repository.save(Permission.builder()
                            .name(perName)
                            .description("Permission: "+ perName)
                            .build())
            );
        }
    }

    private void initRoles(RoleRepository repository){

        List<String> userRoles = List.of(
                "CANBO", "HIEUTRUONG", "QUANTRIVIEN", "QUANLYCAP"
        );

        userRoles.forEach(p-> {
            repository.findByName(p).orElseGet(() -> repository.save(Role.builder()
                    .name(p)
                    .description("Default role: "+p)
                    .build()));
            }
        );
    }

    private void assignPermissionsToAdmin(RoleRepository roleRepository, PermissionRepository permissionRepository){
        Role role = roleRepository.findByName("QUANTRIVIEN").orElseThrow();

        Set<RoleHasPermission> roleHasPermissions = permissionRepository.findAll().stream().map(
                p -> RoleHasPermission.builder()
                        .role(role)
                        .permission(p)
                        .build()
        ).collect(Collectors.toSet());

        role.setRoleHasPermissions(roleHasPermissions);
        roleRepository.save(role);
    }

    private void initAdminUser(UserRepository userRepository, RoleRepository roleRepository){

        if (userRepository.count()>0) return;

        Role role = roleRepository.findByName("QUANTRIVIEN").orElseThrow(
                () -> new RoleNotFoundException("QUANTRIVIEN")
        );

        User user = User.builder()
                .username("000001")
                .password(passwordEncoder.encode("1234565678"))
                .fullName("RootAdmin")
                .dateOfBirth(LocalDate.of(1990, 8, 6))
                .role(role)
                .build();
        userRepository.save(user);
        log.info("Default admin user created: 000001 / 1234565678");
    }
}
