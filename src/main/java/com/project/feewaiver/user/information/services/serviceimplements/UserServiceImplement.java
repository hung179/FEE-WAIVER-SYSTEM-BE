package com.project.feewaiver.user.information.services.serviceimplements;

import com.project.feewaiver.common.enums.ApiType;
import com.project.feewaiver.common.response.ApiResponse;
import com.project.feewaiver.common.response.PageResponse;
import com.project.feewaiver.user.authorization.entities.Role;
import com.project.feewaiver.user.authorization.exceptions.RoleNotFoundException;
import com.project.feewaiver.user.authorization.repositories.RoleRepository;
import com.project.feewaiver.user.information.mappers.UserMapper;
import com.project.feewaiver.user.information.repositories.UserRepository;
import com.project.feewaiver.user.information.dtos.request.UserRequest;
import com.project.feewaiver.user.information.dtos.response.UserResponse;
import com.project.feewaiver.user.information.entities.User;
import com.project.feewaiver.user.information.enums.AccountStatus;
import com.project.feewaiver.user.information.exceptions.UserAlreadyExists;
import com.project.feewaiver.user.information.exceptions.UserNotFoundException;
import com.project.feewaiver.user.information.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('USER_VIEW')")
    public ApiResponse<UserResponse> getUser(String id) {

        UserResponse user = userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
            )
        );

        return ApiResponse.<UserResponse>builder()
                .data(user)
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('USER_VIEW')")
    public ApiResponse<PageResponse<UserResponse>> getAllUser(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<UserResponse> users = userRepository.findAll(pageable).map(userMapper::toUserResponse);

        PageResponse<UserResponse> pageResponse = PageResponse.<UserResponse>builder()
                .totalElement(users.getTotalElements())
                .data(users.getContent())
                .pageSize(users.getSize())
                .currentPage(users.getNumber())
                .build();

        return ApiResponse.<PageResponse<UserResponse>>builder()
                .data(pageResponse)
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasAuthority('USER_CREATE')")
    public ApiResponse<String> createUser(UserRequest request) {
        if(userRepository.existsByUsername(request.getUsername())){
         throw  new UserAlreadyExists(request.getUsername());
        }

            Role role = roleRepository.findByName(request.getRoleName()).orElseThrow(
                    () -> new RoleNotFoundException(request.getRoleName())
            );

            User newUser = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .fullName(request.getFullName())
                    .dateOfBirth(request.getDateOfBirth())
                    .role(role)
                    .build();
            userRepository.save(newUser);

            return ApiResponse.<String>builder()
                    .data("Thêm người dùng thành công.")
                    .success(true)
                    .type(ApiType.SUCCESS)
                    .build();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public ApiResponse<String> updateUser(UserRequest request, String id) {

        User userData = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (request.getUsername() != null && !request.getUsername().isBlank()) {
            userData.setUsername(request.getUsername());
        }
        if (request.getFullName() != null && !request.getFullName().isBlank()) {
            userData.setFullName(request.getFullName());
        }
        if (request.getDateOfBirth() != null) {
            userData.setDateOfBirth(request.getDateOfBirth());
        }

        if (request.getRoleName() != null) {
            Role role = roleRepository.findByName(request.getRoleName()).orElseThrow(
                    () -> new RoleNotFoundException(request.getRoleName())
            );
            userData.setRole(role);
        }

        userRepository.save(userData);

        return ApiResponse.<String>builder()
                .data("Cập nhật người dùng thành công.")
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasAuthority('USER_LOCK')")
    public ApiResponse<String> changeAccountStatus(String ma){
            User userData = userRepository.findById(ma).orElseThrow(
                    () -> new UserNotFoundException(ma)
            );

            AccountStatus accountStatus = (userData.getAccountStatus() == AccountStatus.ACTIVE) ? AccountStatus.INACTIVE : AccountStatus.ACTIVE;

            userData.setAccountStatus(accountStatus);

            userRepository.save(userData);

            return ApiResponse.<String>builder()
                    .data("Cập nhật trạng thái người dùng thành công.")
                    .success(true)
                    .type(ApiType.SUCCESS)
                    .build();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasAuthority('USER_RESET_PASSWORD')")
    public ApiResponse<String> changePassword(String ma, String oldPassword, String newPassword){

        User user = userRepository.findById(ma).orElseThrow(
                () -> new UserNotFoundException(ma)
        );

        if (passwordEncoder.matches(oldPassword, user.getPassword())){
            return new ApiResponse<String>("Mật khẩu cũ không chính xác", false, ApiType.EXISTS);
        }

        if (passwordEncoder.matches(newPassword, user.getPassword())){
            return new ApiResponse<String>("Mật khẩu mới không được giống mật khẩu cũ", false, ApiType.CONFLICT);
        }

        if (newPassword == null || newPassword.trim().length() < 6) {
            return new ApiResponse<String>("Mật khẩu mới phải có ít nhất 6 ký tự", false, ApiType.EXISTS);
        }

        String encodeNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodeNewPassword);

        userRepository.save(user);

        return ApiResponse.<String>builder()
                .data("Cập nhật mật khẩu người dùng thành công.")
                .success(true)
                .type(ApiType.SUCCESS)
                .build();

    }

    @Override
    public ApiResponse<UserResponse> getMyInfo() {
        var context = SecurityContextHolder.getContext();

        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(
                () -> new UserNotFoundException(name)
        );
        return ApiResponse.<UserResponse>builder()
                .data(userMapper.toUserResponse(user))
                .success(true)
                .type(ApiType.SUCCESS)
                .build();
    }
}
