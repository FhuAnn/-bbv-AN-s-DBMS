package com.an.storemanagement.controller;

import com.an.storemanagement.dto.customer.CreateCustomerRequest;
import com.an.storemanagement.dto.customer.CustomerItemResponse;
import com.an.storemanagement.dto.customer.CustomerListResponse;
import com.an.storemanagement.dto.customer.CustomerMetricsResponse;
import com.an.storemanagement.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/metrics")
    public CustomerMetricsResponse getMetrics() {
        return customerService.getMetrics();
    }

    @GetMapping
    public CustomerListResponse getCustomers(
            @RequestParam(defaultValue = "1") @Min(1) int page,
            @RequestParam(defaultValue = "7") @Min(1) @Max(100) int limit,
            @RequestParam(required = false) String search,
            @RequestParam(name = "sort_by", defaultValue = "company_name") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        return customerService.listCustomers(page, limit, search, sortBy, order);
    }

    @PostMapping
    public ResponseEntity<CustomerItemResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest request) {
        CustomerItemResponse created = customerService.createCustomer(request);
        return ResponseEntity.created(URI.create("/api/v1/customers/" + created.id())).body(created);
    }

    @GetMapping(value = "/export", produces = "text/csv")
    public ResponseEntity<String> exportCustomers(@RequestParam(defaultValue = "csv") String format) {
        String fileName = "xlsx".equalsIgnoreCase(format) ? "customers.xlsx" : "customers.csv";
        String csv = customerService.exportCustomersCsv();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(csv);
    }
}