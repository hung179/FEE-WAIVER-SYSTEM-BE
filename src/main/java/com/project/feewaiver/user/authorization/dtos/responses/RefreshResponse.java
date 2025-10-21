package com.project.feewaiver.user.authorization.dtos.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshResponse {

    String token;

    boolean authenticated;

}
