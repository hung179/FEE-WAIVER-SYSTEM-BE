package com.project.feewaiver.user.authorization.repositories;

import com.project.feewaiver.user.authorization.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    public Optional<Role> findByName(String name);
    public boolean existsByName(String name);
}
