package com.an.storemanagement.dto.customer;

import com.an.storemanagement.enums.CustomerStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateCustomerRequest(
                @NotBlank String companyName,
                @NotBlank String domain,
                @NotBlank String category,
                @NotBlank String description,
                CustomerStatus status,
                @Pattern(regexp = "^https?://.*", message = "logoUrl must be a valid URL") String logoUrl) {
}