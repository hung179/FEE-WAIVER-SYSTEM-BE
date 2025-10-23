package com.project.feewaiver.support.expense.entities;

import com.project.feewaiver.common.abstractentity.AbstractEntity;
import com.project.feewaiver.support.beneficiary.entities.Beneficiary;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "expense_support")
@Setter
@Getter
@SuperBuilder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseSupport extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Expense support name is requỉred.")
    String expenseSupportName;

    @NotNull(message = "Expense reduction amount is required.")
    BigDecimal expenseReductionAmount;

    @OneToMany(mappedBy = "expenseSupport", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "beneficiary_id")
    Beneficiary beneficiary;

}
