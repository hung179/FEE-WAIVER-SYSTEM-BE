package com.project.feewaiver.user.information.controllers;

import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.common.response.PageResponse;
import com.project.feewaiver.user.information.dtos.request.UserRequest;
import com.project.feewaiver.user.information.dtos.response.UserResponse;
import com.project.feewaiver.user.information.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/information")
@RequiredArgsConstructor
@Slf4j(topic = "USER-INFORMATION")
public class UserController {

    private final UserService userService;

    @GetMapping("/getAll")
    public ApiResponse<PageResponse<UserResponse>> getAllUser(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size){

        return userService.getAllUser(page, size);
    }

    @GetMapping("/get/{id}")
    public ApiResponse<UserResponse> getUser(
            @PathVariable String id){
         return userService.getUser(id);
    }

    @PostMapping("/create")
    public ApiResponse<String> createUser(
            @RequestPart("user") @Valid UserRequest user){
        log.info("Create user controller start ...!");
        return userService.createUser(user);
    }

    @PatchMapping("update/{id}")
    public ApiResponse<String> updateUser(
            @RequestPart UserRequest user,
            @PathVariable String id){
        log.info("Update user controller start ...!");
        return userService.updateUser(user, id);
    }

    @PostMapping("changeStatus/{id}")
    public ApiResponse<String> changeAccountStatus(@PathVariable String id){
        log.info("Change user account status controller start ...!");
        return userService.changeAccountStatus(id);
    }

    @PostMapping("changePassword/{id}")
    public ApiResponse<String> changeAccountPassword(
            @PathVariable String id,
            @RequestPart String oldPassword,
            @RequestPart String newPassword
            ){
        log.info("Change Password user controller start ...!");
        return userService.changePassword(id, oldPassword, newPassword);
    }

    @GetMapping("/myInfo")
    @PostAuthorize("returnObject.data.username == authentication.name")
    public ApiResponse<?> getMyInfo() {
        return userService.getMyInfo();
    }
}
