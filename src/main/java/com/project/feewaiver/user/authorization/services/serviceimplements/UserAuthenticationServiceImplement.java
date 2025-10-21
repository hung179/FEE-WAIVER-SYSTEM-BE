package com.project.feewaiver.user.authorization.services.serviceimplements;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.project.feewaiver.common.configuration.JwtProperties;
import com.project.feewaiver.user.authorization.dtos.requests.AuthenticationRequest;
import com.project.feewaiver.user.authorization.dtos.requests.IntrospectRequest;
import com.project.feewaiver.user.authorization.dtos.requests.LogoutRequest;
import com.project.feewaiver.user.authorization.dtos.requests.RefreshRequest;
import com.project.feewaiver.user.authorization.dtos.responses.AuthenticationResponse;
import com.project.feewaiver.user.authorization.dtos.responses.IntrospectResponse;
import com.project.feewaiver.user.authorization.dtos.responses.RefreshResponse;
import com.project.feewaiver.user.authorization.entities.UserInvalidatedToken;
import com.project.feewaiver.user.authorization.exceptions.UnauthenticatedException;
import com.project.feewaiver.user.authorization.repositories.UserInvalidatedTokenRepository;
import com.project.feewaiver.user.authorization.services.UserAuthenticationService;
import com.project.feewaiver.user.information.entities.User;
import com.project.feewaiver.user.information.exceptions.UserNotFoundException;
import com.project.feewaiver.user.information.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAuthenticationServiceImplement implements UserAuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserInvalidatedTokenRepository userInvalidatedTokenRepository;

    private final JwtProperties jwtProperties;



    @Override
    public IntrospectResponse introspect(IntrospectRequest request) {

        String token = request.getToken();

        boolean isValid = true;

        try {
            verifyToken(token, false);
        }catch (Exception e){
            isValid = false;
        }
        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }

    @Override
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(request.getToken(), true);
            String jit = signToken.getJWTClaimsSet().getJWTID();

            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            UserInvalidatedToken invalidatedToken = UserInvalidatedToken.builder()
                    .id(jit)
                    .expiryTime(expiryTime)
                    .build();
            userInvalidatedTokenRepository.save(invalidatedToken);
        }catch (Exception e){
            log.info("Token already expired");
        }

    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {

        JWSVerifier verifier = new MACVerifier(jwtProperties.getSignerKey().getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isRefresh)
                ? new Date(signedJWT.getJWTClaimsSet().getIssueTime().toInstant().plus(jwtProperties.getRefreshableDuration(), ChronoUnit.SECONDS).toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date()))){
            throw new UnauthenticatedException("Lỗi token logout");
        }

        if (userInvalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())){
            throw new UnauthenticatedException("Lỗi token logout");
        }

        return signedJWT;
    }

    @Override
    public AuthenticationResponse authenticate (AuthenticationRequest request){

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new UserNotFoundException(request.getUsername())
        );

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new UnauthenticatedException("Sai mật khẩu hoặc tài khoản");
        }

        String token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    @Override
    public RefreshResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var signJWT = verifyToken(request.getToken(), true);

        var jit = signJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signJWT.getJWTClaimsSet().getExpirationTime();

        UserInvalidatedToken invalidatedToken = UserInvalidatedToken.builder()
                .id(jit)
                .expiryTime(expiryTime)
                .build();
        userInvalidatedTokenRepository.save(invalidatedToken);
        var username = signJWT.getJWTClaimsSet().getSubject();
        var user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(username)
        );
        String token = generateToken(user);

        return RefreshResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String generateToken(User user){
        try{

            JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

            JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUsername())
                    .issuer("QLTT-api")
                    .issueTime(new Date())
                    .expirationTime(
                            new Date(
                                    Instant.now()
                                            .plus(jwtProperties.getValidDuration(), ChronoUnit.SECONDS)
                                            .toEpochMilli()
                            )
                    )
                    .jwtID(UUID.randomUUID().toString())
                    .claim("scope", buildScope(user))
                    .build();
            Payload payload = new Payload(jwtClaimsSet.toJSONObject());

            JWSObject jwsObject = new JWSObject(header, payload);

            jwsObject.sign(new MACSigner(jwtProperties.getSignerKey().getBytes()));
            return jwsObject.serialize();
        }catch (JOSEException e){
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (user.getRole()!=null){
            stringJoiner.add("ROLE_" + user.getRole().getName());
            if (!CollectionUtils.isEmpty(user.getRole().getRoleHasPermissions())){
                user.getRole().getRoleHasPermissions().forEach(permission -> stringJoiner.add(permission.getPermission().getName()));
            }
        }
        return stringJoiner.toString();
    }
}
