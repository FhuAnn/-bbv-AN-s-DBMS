package com.an.storemanagement.dto.customer;

import java.util.List;

public record CustomerMetricsResponse(String status, CustomerMetricsData data) {

    public record CustomerMetricsData(MetricDetail totalCustomers, MetricDetail members, ActiveNow activeNow) {
    }

    public record MetricDetail(int value, double changePercentage, String trend) {
    }

    public record ActiveNow(int value, List<UserAvatar> recentActiveUsers) {
    }

    public record UserAvatar(String id, String name, String avatarUrl) {
    }
}