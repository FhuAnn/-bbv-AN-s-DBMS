package com.an.storemanagement.repository;
import com.an.storemanagement.dto.customer.CreateCustomerRequest;
import com.an.storemanagement.dto.customer.CustomerItemResponse;
import com.an.storemanagement.dto.customer.CustomerListResponse;
import com.an.storemanagement.dto.customer.CustomerMetricsResponse;

public interface CustomerRepository {

    CustomerMetricsResponse getMetrics();

    CustomerListResponse listCustomers(int page, int limit, String search, String sortBy, String order);

    CustomerItemResponse createCustomer(CreateCustomerRequest request);

    String exportCustomersCsv();
}