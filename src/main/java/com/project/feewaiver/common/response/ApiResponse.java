package com.project.feewaiver.common.response;


import com.project.feewaiver.common.enums.ApiType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ApiResponse<T> {

    T data;

    Boolean success;

    ApiType type;

}
