package com.project.feewaiver.user.authorization.services;

import com.nimbusds.jose.JOSEException;
import com.project.feewaiver.user.authorization.dtos.requests.AuthenticationRequest;
import com.project.feewaiver.user.authorization.dtos.requests.IntrospectRequest;
import com.project.feewaiver.user.authorization.dtos.requests.LogoutRequest;
import com.project.feewaiver.user.authorization.dtos.requests.RefreshRequest;
import com.project.feewaiver.user.authorization.dtos.responses.AuthenticationResponse;
import com.project.feewaiver.user.authorization.dtos.responses.IntrospectResponse;
import com.project.feewaiver.user.authorization.dtos.responses.RefreshResponse;

import java.text.ParseException;

public interface UserAuthenticationService {

    IntrospectResponse introspect(IntrospectRequest request);

    void logout(LogoutRequest request) throws ParseException, JOSEException;

    AuthenticationResponse authenticate (AuthenticationRequest request);

    RefreshResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;

}
