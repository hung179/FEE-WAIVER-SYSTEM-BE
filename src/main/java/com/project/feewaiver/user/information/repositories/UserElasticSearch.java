package com.project.feewaiver.user.information.repositories;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serial;
import java.io.Serializable;

@Document(indexName = "nguoidung")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserElasticSearch implements Serializable {

    @Serial
    static final Long serialVersionUID = -5257626960164837310L;

    @Field(name = "id", type = FieldType.Long)
    Long id;

    @Field(name = "accountStatus", type = FieldType.Long)
    String accountStatus;
}
