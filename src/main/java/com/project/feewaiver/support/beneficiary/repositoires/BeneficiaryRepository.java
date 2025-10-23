package com.project.feewaiver.support.beneficiary.repositoires;

import com.project.feewaiver.support.beneficiary.entities.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long>, JpaSpecificationExecutor<Beneficiary> {

    boolean existsByExpenseSupport_ExpenseSupportNameAndTuitionSupport_TuitionSupportName(String expenseSupportName, String tuitionSupportName);

}
