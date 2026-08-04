package com.an.storemanagement.service;

import com.an.storemanagement.dto.user.CurrentUserResponse;
import com.an.storemanagement.dto.user.UpdateCurrentUserRequest;
import com.an.storemanagement.model.CurrentUserProfile;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private CurrentUserProfile currentUser = new CurrentUserProfile(
            "user-001",
            "Sophia Munn",
            "sophia@untitled.com",
            "https://i.pravatar.cc/150?u=sophia",
            "STORE_ADMIN",
            "store-001");

    public CurrentUserResponse getCurrentUser(String userId) {
        return toResponse(currentUser);
    }

    public CurrentUserResponse updateCurrentUser(String userId, UpdateCurrentUserRequest request) {
        currentUser = new CurrentUserProfile(
                currentUser.id(),
                request.fullName() == null || request.fullName().isBlank() ? currentUser.fullName()
                        : request.fullName(),
                currentUser.email(),
                request.avatarUrl() == null ? currentUser.avatarUrl() : request.avatarUrl(),
                currentUser.role(),
                currentUser.storeId());
        return toResponse(currentUser);
    }

    private CurrentUserResponse toResponse(CurrentUserProfile profile) {
        return new CurrentUserResponse(new CurrentUserResponse.Data(
                profile.id(),
                profile.fullName(),
                profile.email(),
                profile.avatarUrl(),
                profile.role(),
                profile.storeId()));
    }
}