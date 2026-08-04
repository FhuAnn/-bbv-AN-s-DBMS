package com.an.storemanagement.dto.user;

public record CurrentUserResponse(Data data) {

        public record Data(String id, String fullName, String email, String avatarUrl, String role, String storeId) {
        }
}