package com.an.storemanagement.controller;

import com.an.storemanagement.dto.customer.BulkCustomerIdsRequest;
import com.an.storemanagement.dto.customer.BulkOperationResponse;
import com.an.storemanagement.dto.customer.CreateCustomerRequest;
import com.an.storemanagement.dto.customer.CustomerPageResponse;
import com.an.storemanagement.dto.customer.CustomerResponse;
import com.an.storemanagement.dto.customer.CustomerSummaryResponse;
import com.an.storemanagement.dto.customer.UpdateCustomerRequest;
import com.an.storemanagement.enums.CustomerStatus;
import com.an.storemanagement.service.CustomerService;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/summary")
    public CustomerSummaryResponse getSummary() {
        return customerService.getSummary();
    }

    @GetMapping
    public CustomerPageResponse getCustomers(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false, name = "size") Integer pageSize,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) CustomerStatus status,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDirection) {
        return customerService.getCustomers(page, pageSize, search, status, sortBy, sortDirection);
    }

    @GetMapping("/{customerId}")
    public CustomerResponse getCustomer(@PathVariable UUID customerId) {
        return customerService.getCustomer(customerId);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest request) {
        CustomerResponse response = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/api/v1/customers/" + response.data().id())
                .body(response);
    }

    @PatchMapping("/{customerId}")
    public CustomerResponse updateCustomer(
            @PathVariable UUID customerId,
            @Valid @RequestBody UpdateCustomerRequest request) {
        return customerService.updateCustomer(customerId, request);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/bulk-delete")
    public BulkOperationResponse bulkDelete(@Valid @RequestBody BulkCustomerIdsRequest request) {
        return customerService.bulkDelete(request);
    }

    @GetMapping(value = "/export", produces = { "text/csv",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" })
    public ResponseEntity<byte[]> exportCustomers(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) CustomerStatus status,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDirection,
            @RequestParam(required = false, defaultValue = "csv") String format) {
        byte[] file = customerService.exportCustomers(search, status, sortBy, sortDirection, format);
        MediaType mediaType = "xlsx".equalsIgnoreCase(format)
                ? MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                : MediaType.parseMediaType("text/csv");

        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=customers." + format.toLowerCase())
                .body(file);
    }
}