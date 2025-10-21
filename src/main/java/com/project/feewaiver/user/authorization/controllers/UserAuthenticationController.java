package com.project.feewaiver.user.authorization.controllers;

import com.nimbusds.jose.JOSEException;
import com.project.feewaiver.common.enums.ApiType;
import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.user.authorization.dtos.requests.AuthenticationRequest;
import com.project.feewaiver.user.authorization.dtos.requests.IntrospectRequest;
import com.project.feewaiver.user.authorization.dtos.requests.LogoutRequest;
import com.project.feewaiver.user.authorization.dtos.requests.RefreshRequest;
import com.project.feewaiver.user.authorization.dtos.responses.AuthenticationResponse;
import com.project.feewaiver.user.authorization.dtos.responses.IntrospectResponse;
import com.project.feewaiver.user.authorization.dtos.responses.RefreshResponse;
import com.project.feewaiver.user.authorization.services.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/user/auth")
@RequiredArgsConstructor
public class UserAuthenticationController {

    private final UserAuthenticationService userAuthenticationService;


    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate (
            @RequestBody AuthenticationRequest request
            ){
        AuthenticationResponse response = userAuthenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .data(response)
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(
            @RequestBody IntrospectRequest request){
        IntrospectResponse response = userAuthenticationService.introspect(request);

        return ApiResponse.<IntrospectResponse>builder()
                .data(response)
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(
            @RequestBody LogoutRequest request
            ) throws ParseException, JOSEException {
        userAuthenticationService.logout(request);
        return ApiResponse.<String>builder()
                .data("Đăng xuất thành công!")
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

    @PostMapping("/refresh")
    public ApiResponse<RefreshResponse> refreshToken (
            @RequestBody RefreshRequest request
    ) throws ParseException, JOSEException {
        RefreshResponse response = userAuthenticationService.refreshToken(request);
        return ApiResponse.<RefreshResponse>builder()
                .data(response)
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

}
