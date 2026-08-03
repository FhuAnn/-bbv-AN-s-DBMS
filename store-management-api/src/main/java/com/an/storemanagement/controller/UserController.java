package com.an.storemanagement.controller;

import com.an.storemanagement.dto.user.CurrentUserResponse;
import com.an.storemanagement.dto.user.UpdateCurrentUserRequest;
import com.an.storemanagement.service.UserService;
import jakarta.validation.Valid;
import java.security.Principal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public CurrentUserResponse getCurrentUser(Authentication authentication) {
        return userService.getCurrentUser(authentication.getName());
    }

    @PatchMapping("/me")
    public ResponseEntity<CurrentUserResponse> updateCurrentUser(Authentication authentication,
            @Valid @RequestBody UpdateCurrentUserRequest request) {
        return ResponseEntity.ok(userService.updateCurrentUser(authentication.getName(), request));
    }
}