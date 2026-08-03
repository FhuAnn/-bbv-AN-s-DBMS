package com.an.storemanagement.dto.customer;

public record PaginationResponse(
        int currentPage,
        int perPage,
        int totalRecords,
        int totalPages,
        boolean hasNext,
        boolean hasPrevious) {
}