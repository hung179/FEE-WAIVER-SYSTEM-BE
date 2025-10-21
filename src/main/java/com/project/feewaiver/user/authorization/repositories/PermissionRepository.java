package com.project.feewaiver.user.authorization.repositories;

import com.project.feewaiver.user.authorization.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {

    List<Permission> findAllByNameIn(Set<String> name);

    Optional<Permission> findByName(String name);

}
