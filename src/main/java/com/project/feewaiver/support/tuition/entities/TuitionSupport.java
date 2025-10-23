package com.project.feewaiver.support.tuition.entities;

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
@Table(name = "ho_tro_hoc_phi")
@Setter
@Getter
@SuperBuilder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class TuitionSupport extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Tuition support name is requỉred.")
    String tuitionSupportName;

    @NotNull(message = "Tuition reduction rate is required.")
    BigDecimal tuitionReductionRate;

    @NotNull(message = "Tuition reduction amount is required.")
    BigDecimal tuitionReductionAmount;

    @OneToMany(mappedBy = "tuitionSupport", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "beneficiary_id")
    Beneficiary beneficiary;

}
