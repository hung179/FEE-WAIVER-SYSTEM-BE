package com.project.feewaiver.user.information.entities;

import com.project.feewaiver.common.abstractentity.AbstractEntity;
import com.project.feewaiver.manage.organization.entities.Organization;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_DETAIL")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetail extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    Organization organization;

}
