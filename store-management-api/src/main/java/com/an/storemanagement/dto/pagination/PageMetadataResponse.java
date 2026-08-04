package com.an.storemanagement.dto.pagination;

public record PageMetadataResponse(
    int number,
    int size,
    long totalElements,
    int totalPages,
    boolean first,
    boolean last,
    boolean hasNext,
    boolean hasPrevious
) {
}
