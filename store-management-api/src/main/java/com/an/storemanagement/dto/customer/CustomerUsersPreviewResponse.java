package com.an.storemanagement.dto.customer;

import java.util.List;

import com.an.storemanagement.dto.user.UserPreviewResponse;

public record CustomerUsersPreviewResponse(
        int totalCount,
        List<UserPreviewResponse> preview,
        int remainingCount) {
}
