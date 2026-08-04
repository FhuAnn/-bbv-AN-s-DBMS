package com.an.storemanagement.controller;

import com.an.storemanagement.dto.user.CurrentUserResponse;
import com.an.storemanagement.dto.user.UpdateCurrentUserRequest;
import com.an.storemanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public CurrentUserResponse getCurrentUser(Authentication authentication) {
        return userService.getCurrentUser(authentication.getName());
    }

    @PatchMapping("/me")
    public CurrentUserResponse updateCurrentUser(
            Authentication authentication,
            @Valid @RequestBody UpdateCurrentUserRequest request) {
        return userService.updateCurrentUser(authentication.getName(), request);
    }
}
