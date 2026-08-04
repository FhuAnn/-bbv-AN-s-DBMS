package com.an.storemanagement.dto.customer;

import com.an.storemanagement.enums.CustomerStatus;

public record CustomerFilterRequest(
        Integer page,
        Integer size,
        String search,
        CustomerStatus status,
        String sortBy,
        String sortDirection) {
}
