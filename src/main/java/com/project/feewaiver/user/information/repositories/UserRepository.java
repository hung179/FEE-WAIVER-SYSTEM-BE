package com.project.feewaiver.user.information.repositories;


import com.project.feewaiver.user.information.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    Optional <User> findByUsername(String username);

    boolean existsByUsername(String username);

}
