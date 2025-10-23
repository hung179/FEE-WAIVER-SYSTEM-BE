package com.project.feewaiver.support.dtos.responses;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TuitionSupportResponse {

    String tuitionSupportName;

    BigDecimal tuitionReductionRate;

    BigDecimal tuitionReductionAmount;

}
