package com.project.feewaiver.support.dtos.requests;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TuitionSupportRequest {

    @Size(max = 100, message = "Tuition support name must not exceed 100 characters")
    String tuitionSupportName;

    @DecimalMin(value = "0.0", inclusive = true, message = "Tuition reduction rate must be greater than or equal to 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "Tuition reduction rate must not exceed 100")
    BigDecimal tuitionReductionRate;

    @DecimalMin(value = "0.0", inclusive = true, message = "Tuition reduction amount must be greater than or equal to 0")
    BigDecimal tuitionReductionAmount;

}
