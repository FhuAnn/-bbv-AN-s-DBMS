package com.an.storemanagement.dto.customer;

import com.an.storemanagement.enums.CustomerStatus;
import jakarta.validation.constraints.Pattern;

public record UpdateCustomerRequest(
        String companyName,
        String domain,
        @Pattern(regexp = "^https?://.*", message = "logoUrl must be a valid URL") String logoUrl,
        String category,
        String description,
        CustomerStatus status) {
}