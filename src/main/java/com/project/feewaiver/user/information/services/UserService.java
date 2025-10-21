package com.project.feewaiver.user.information.services;


import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.common.response.PageResponse;
import com.project.feewaiver.user.information.dtos.request.UserRequest;
import com.project.feewaiver.user.information.dtos.response.UserResponse;

public interface UserService {

    ApiResponse<PageResponse<UserResponse>> getAllUser(Integer page, Integer size);

    ApiResponse<UserResponse> getUser(String id);

    ApiResponse<String> createUser(UserRequest request);

    ApiResponse<String> updateUser(UserRequest request, String id);

    ApiResponse<String> changeAccountStatus(String id);

    ApiResponse<String> changePassword(String id, String oldPassword, String newPassword);

    ApiResponse<UserResponse> getMyInfo();
}
