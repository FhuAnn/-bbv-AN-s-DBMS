package com.an.storemanagement.dto.customer;

import java.util.List;

public record CustomerItemResponse(
        String id,
        Company company,
        String status,
        About about,
        Users users) {

    public record Company(String name, String domain, String logoUrl) {
    }

    public record About(String category, String description) {
    }

    public record Users(int totalCount, List<UserPreview> preview, int remainingCount) {
    }

    public record UserPreview(String id, String name, String avatarUrl) {
    }
}