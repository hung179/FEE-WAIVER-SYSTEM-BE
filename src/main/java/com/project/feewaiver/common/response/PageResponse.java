package com.project.feewaiver.common.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse <T> implements Serializable {

    Integer currentPage;

    Integer pageSize;

    Integer totalPage;

    Long totalElement;

    @Builder.Default
    private List<T> data = Collections.emptyList();

}
