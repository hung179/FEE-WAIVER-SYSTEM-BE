package com.project.feewaiver.support.tuition.mappers;

import com.project.feewaiver.support.tuition.entities.TuitionSupport;
import com.project.feewaiver.support.dtos.requests.TuitionSupportRequest;
import com.project.feewaiver.support.dtos.responses.TuitionSupportResponse;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TuitionSupportMapper {

    @Mapping(target = "id", ignore = true)
    TuitionSupport toTuitionSupport(TuitionSupportRequest request);

    TuitionSupportResponse toTuitionSupportResponse(TuitionSupport tuitionSupport);

}
