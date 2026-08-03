package com.an.storemanagement.dto.user;

import jakarta.validation.constraints.Pattern;

public record UpdateCurrentUserRequest(String fullName,
        @Pattern(regexp = "^https?://.*", message = "avatarUrl must be a valid URL") String avatarUrl) {
}