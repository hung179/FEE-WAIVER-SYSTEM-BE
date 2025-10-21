package com.project.feewaiver.common.configuration.sercurity;

import com.project.feewaiver.common.configuration.JwtProperties;
import com.project.feewaiver.user.authorization.dtos.requests.IntrospectRequest;
import com.project.feewaiver.user.authorization.dtos.responses.IntrospectResponse;
import com.project.feewaiver.user.authorization.services.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CustomJwtDecoder implements JwtDecoder {

    private final JwtProperties jwtProperties;

    private final UserAuthenticationService authenticationService;

    private NimbusJwtDecoder nimbusJwtDecoder;

    @Override
    public Jwt decode(String token) throws JwtException {
        IntrospectResponse response = authenticationService.introspect(
                IntrospectRequest.builder()
                        .token(token)
                        .build());
        if (!response.isValid()) {
            throw new JwtException("Token invalid");
        }
        if (Objects.isNull(nimbusJwtDecoder)) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(jwtProperties.getSignerKey().getBytes(), "HS512");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }
        return nimbusJwtDecoder.decode(token);
    }

}
