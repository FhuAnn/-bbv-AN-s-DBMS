package com.an.storemanagement.service;

import com.an.storemanagement.dto.user.CurrentUserResponse;
import com.an.storemanagement.dto.user.UpdateCurrentUserRequest;
import com.an.storemanagement.model.CurrentUserProfile;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public CurrentUserResponse getCurrentUser(String userId) {
        return null;
    }

    public CurrentUserResponse updateCurrentUser(String userId, UpdateCurrentUserRequest request) {
        return null;
    }

    private CurrentUserResponse toResponse(CurrentUserProfile profile) {
        return null;
    }
}