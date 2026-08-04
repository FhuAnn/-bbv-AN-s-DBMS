package com.an.storemanagement.dto.customer;

public record CustomerSummary(MetricResponse totalCustomers, MetricResponse members, ActiveNowResponse activeNow) {
}