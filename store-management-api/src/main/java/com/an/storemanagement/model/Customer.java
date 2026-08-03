package com.an.storemanagement.model;

import java.time.Instant;
import java.util.List;

public record Customer(
        String id,
        CustomerCompany company,
        String status,
        String category,
        String description,
        List<CustomerUserPreview> users,
        Instant createdAt) {
}