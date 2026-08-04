package com.an.storemanagement.dto.customer;

import java.util.List;

public record BulkOperationResponse(Data data) {

    public record Data(int requestedCount, long succeededCount, List<String> failedIds) {
    }
}