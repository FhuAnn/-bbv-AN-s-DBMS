package com.an.storemanagement.dto.customer;

import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequest(
        @NotBlank String companyName,
        @NotBlank String domain,
        @NotBlank String category,
        String description) {
}