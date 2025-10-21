package com.project.feewaiver.user.authorization.repositories;

import com.project.feewaiver.user.authorization.entities.UserInvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserInvalidatedTokenRepository extends JpaRepository<UserInvalidatedToken, String>, JpaSpecificationExecutor<UserInvalidatedToken> {
}
