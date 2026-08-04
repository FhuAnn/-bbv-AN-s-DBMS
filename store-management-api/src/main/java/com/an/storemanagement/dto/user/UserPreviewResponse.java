package com.an.storemanagement.dto.user;

import java.util.UUID;

public record UserPreviewResponse(
    UUID id,
    String displayName,
    String avatarUrl
) {
}
