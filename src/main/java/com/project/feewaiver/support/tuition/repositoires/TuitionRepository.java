package com.project.feewaiver.support.tuition.repositoires;

import com.project.feewaiver.support.tuition.entities.TuitionSupport;
import com.project.feewaiver.user.information.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface TuitionRepository extends JpaRepository<TuitionSupport, Long>, JpaSpecificationExecutor<TuitionSupport> {

    Optional<TuitionSupport> findByTuitionSupportName(String tuitionSupportName);

    boolean existsByTuitionSupportName(String tuitionSupportName);

}
