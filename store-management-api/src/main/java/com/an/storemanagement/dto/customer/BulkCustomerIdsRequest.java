package com.an.storemanagement.dto.customer;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record BulkCustomerIdsRequest(@NotEmpty List<String> customerIds) {
}