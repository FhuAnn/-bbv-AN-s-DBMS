package com.an.storemanagement.dto.customer;

import java.util.List;

public record CustomerUsersPreview(int totalCount, List<UserAvatar> preview, int remainingCount) {
}