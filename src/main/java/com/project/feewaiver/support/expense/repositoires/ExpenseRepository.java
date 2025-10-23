package com.project.feewaiver.support.expense.repositoires;

import com.project.feewaiver.support.expense.entities.ExpenseSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<ExpenseSupport, Long>, JpaSpecificationExecutor<ExpenseSupport> {

    Optional<ExpenseSupport> findByExpenseSupportName(String name);

    boolean existsByExpenseSupportName(String expenseSupportName);

}
