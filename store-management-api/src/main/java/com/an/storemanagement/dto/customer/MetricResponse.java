package com.an.storemanagement.dto.customer;

public record MetricResponse(
        long value,
        double changePercentage,
        Trend trend) {
    public enum Trend {
        UP,
        DOWN,
        UNCHANGED
    }
}
