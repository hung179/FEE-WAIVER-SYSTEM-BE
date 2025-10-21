package com.project.feewaiver.common.abstractentity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class AbstractEntity implements Serializable {

    @CreationTimestamp
    @Column(name = "create_at")
    LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updateAt;

    @CreatedBy
    @Column(name = "create_by")
    String createdBy;

    @LastModifiedBy
    @Column(name = "update_by")
    String updateBy;

}
