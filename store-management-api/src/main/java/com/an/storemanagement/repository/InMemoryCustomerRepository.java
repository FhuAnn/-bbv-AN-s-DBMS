package com.an.storemanagement.repository;
import com.an.storemanagement.dto.customer.CreateCustomerRequest;
import com.an.storemanagement.dto.customer.CustomerItemResponse;
import com.an.storemanagement.dto.customer.CustomerListResponse;
import com.an.storemanagement.dto.customer.CustomerMetricsResponse;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

    @Override
    public CustomerMetricsResponse getMetrics() {
        return null;
    }

    @Override
    public CustomerListResponse listCustomers(int page, int limit, String search, String sortBy, String order) {
        return null;
    }

    @Override
    public CustomerItemResponse createCustomer(CreateCustomerRequest request) {
        return null;
    }

    @Override
    public String exportCustomersCsv() {
        return "";
    }
}