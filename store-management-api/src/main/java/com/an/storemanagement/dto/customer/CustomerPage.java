package com.an.storemanagement.dto.customer;

import com.an.storemanagement.dto.pagination.PageMetadataResponse;
import java.util.List;

public record CustomerPage(List<CustomerTableItem> items, PageMetadataResponse page) {
}