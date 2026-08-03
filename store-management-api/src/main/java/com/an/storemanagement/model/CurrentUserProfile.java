package com.an.storemanagement.model;

public record CurrentUserProfile(
        String id,
        String fullName,
        String email,
        String avatarUrl,
        String role,
        String storeId) {
}