package com.an.storemanagement.dto.user;

public record CurrentUserResponse(
        String id,
        String fullName,
        String email,
        String avatarUrl,
        String role,
        String storeId) {
}