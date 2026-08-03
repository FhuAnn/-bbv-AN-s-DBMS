package com.an.storemanagement.dto.customer;

import java.util.List;

public record CustomerListResponse(String status, List<CustomerItemResponse> data, PaginationResponse pagination) {
}