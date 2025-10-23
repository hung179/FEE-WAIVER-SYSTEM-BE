package com.project.feewaiver.support.beneficiary.entities;

import com.project.feewaiver.common.abstractentity.AbstractEntity;
import com.project.feewaiver.support.expense.entities.ExpenseSupport;
import com.project.feewaiver.support.tuition.entities.TuitionSupport;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "beneficiary")
@Setter
@Getter
@SuperBuilder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Beneficiary extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @NotNull(message = "Expense support is required.")
    @JoinColumn(name = "expense_support_id")
    ExpenseSupport expenseSupport;

    @ManyToOne
    @JoinColumn(name = "tuition_support_id")
    @NotNull(message = "Tuition support is required.")
    TuitionSupport tuitionSupport;

}
