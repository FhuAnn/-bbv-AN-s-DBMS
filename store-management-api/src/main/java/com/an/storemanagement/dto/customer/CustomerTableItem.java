package com.an.storemanagement.dto.customer;

public record CustomerTableItem(String id, CompanySummary company, com.an.storemanagement.enums.CustomerStatus status,
        CustomerAbout about, CustomerUsersPreview users) {
}