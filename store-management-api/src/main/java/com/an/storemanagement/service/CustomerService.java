package com.an.storemanagement.service;

import com.an.storemanagement.dto.customer.CreateCustomerRequest;
import com.an.storemanagement.dto.customer.CustomerItemResponse;
import com.an.storemanagement.dto.customer.CustomerListResponse;
import com.an.storemanagement.dto.customer.CustomerMetricsResponse;
import com.an.storemanagement.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerMetricsResponse getMetrics() {
        return customerRepository.getMetrics();
    }

    public CustomerListResponse listCustomers(int page, int limit, String search, String sortBy, String order) {
        return customerRepository.listCustomers(page, limit, search, sortBy, order);
    }

    public CustomerItemResponse createCustomer(CreateCustomerRequest request) {
        return customerRepository.createCustomer(request);
    }

    public String exportCustomersCsv() {
        return customerRepository.exportCustomersCsv();
    }
}