package com.an.storemanagement.dto.customer;

import java.util.List;

public record ActiveNowResponse(
        long value,
        List<UserAvatar> previewUsers) {
}
