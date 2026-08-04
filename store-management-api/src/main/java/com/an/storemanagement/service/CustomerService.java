package com.an.storemanagement.service;

import com.an.storemanagement.dto.customer.ActiveNowResponse;
import com.an.storemanagement.dto.customer.BulkCustomerIdsRequest;
import com.an.storemanagement.dto.customer.BulkOperationResponse;
import com.an.storemanagement.dto.customer.CompanySummary;
import com.an.storemanagement.dto.customer.CreateCustomerRequest;
import com.an.storemanagement.dto.customer.CustomerAbout;
import com.an.storemanagement.dto.customer.CustomerPage;
import com.an.storemanagement.dto.customer.CustomerPageResponse;
import com.an.storemanagement.dto.customer.CustomerResponse;
import com.an.storemanagement.dto.customer.CustomerSummary;
import com.an.storemanagement.dto.customer.CustomerSummaryResponse;
import com.an.storemanagement.dto.customer.CustomerTableItem;
import com.an.storemanagement.dto.customer.CustomerUsersPreview;
import com.an.storemanagement.dto.customer.MetricResponse;
import com.an.storemanagement.dto.customer.UpdateCustomerRequest;
import com.an.storemanagement.dto.customer.UserAvatar;
import com.an.storemanagement.enums.CustomerStatus;
import com.an.storemanagement.exception.CustomerNotFoundException;
import com.an.storemanagement.exception.DuplicateCustomerException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final Map<UUID, CustomerRecord> customers = new LinkedHashMap<>();

    public CustomerService() {
        seed();
    }

    public CustomerSummaryResponse getSummary() {
        List<CustomerRecord> filtered = new ArrayList<>(customers.values());
        long totalCustomers = filtered.size();
        long members = 1210;
        List<UserAvatar> previewUsers = List.of(
                new UserAvatar("usr_101", "Olivia", "https://i.pravatar.cc/150?u=usr_101"),
                new UserAvatar("usr_102", "Phoenix", "https://i.pravatar.cc/150?u=usr_102"),
                new UserAvatar("usr_103", "Lana", "https://i.pravatar.cc/150?u=usr_103"),
                new UserAvatar("usr_104", "Demi", "https://i.pravatar.cc/150?u=usr_104"),
                new UserAvatar("usr_105", "Candice", "https://i.pravatar.cc/150?u=usr_105"));

        return new CustomerSummaryResponse(new CustomerSummary(
                new MetricResponse(totalCustomers, 20.0, MetricResponse.Trend.UP),
                new MetricResponse(members, 15.0, MetricResponse.Trend.UP),
                new ActiveNowResponse(316, previewUsers)));
    }

    public CustomerPageResponse getCustomers(Integer page, Integer size, String search, CustomerStatus status,
            String sortBy, String sortDirection) {
        int resolvedPage = page == null || page < 1 ? 1 : page;
        int resolvedSize = size == null || size < 1 ? 7 : Math.min(size, 100);

        List<CustomerRecord> filtered = customers.values().stream()
                .filter(customer -> matchesSearch(customer, search))
                .filter(customer -> status == null || customer.status == status)
                .collect(Collectors.toList());

        int totalElements = filtered.size();
        int totalPages = (int) Math.ceil(totalElements / (double) resolvedSize);
        int fromIndex = Math.min((resolvedPage - 1) * resolvedSize, totalElements);
        int toIndex = Math.min(fromIndex + resolvedSize, totalElements);

        List<CustomerTableItem> items = filtered.subList(fromIndex, toIndex).stream()
                .map(this::toTableItem)
                .toList();

        return new CustomerPageResponse(new CustomerPage(
                items,
                new com.an.storemanagement.dto.pagination.PageMetadataResponse(
                        resolvedPage,
                        resolvedSize,
                        totalElements,
                        totalPages,
                        resolvedPage == 1,
                        resolvedPage >= totalPages && totalPages > 0,
                        resolvedPage < totalPages,
                        resolvedPage > 1)));
    }

    public CustomerResponse getCustomer(UUID customerId) {
        return new CustomerResponse(toTableItem(findCustomer(customerId)));
    }

    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        if (customers.values().stream().anyMatch(customer -> customer.domain.equalsIgnoreCase(request.domain()))) {
            throw new DuplicateCustomerException(request.domain());
        }

        UUID id = UUID.randomUUID();
        CustomerRecord created = new CustomerRecord(id, request.companyName(), request.domain(), null,
                CustomerStatus.valueOf(request.status() == null ? "CUSTOMER" : request.status().name()),
                request.category(), request.description(), List.of(), Instant.now(), Instant.now());
        customers.put(id, created);
        return new CustomerResponse(toTableItem(created));
    }

    public CustomerResponse updateCustomer(UUID customerId, UpdateCustomerRequest request) {
        CustomerRecord existing = findCustomer(customerId);
        CustomerRecord updated = existing.withUpdates(request);
        customers.put(customerId, updated);
        return new CustomerResponse(toTableItem(updated));
    }

    public void deleteCustomer(UUID customerId) {
        if (customers.remove(customerId) == null) {
            throw new CustomerNotFoundException(customerId.toString());
        }
    }

    public BulkOperationResponse bulkDelete(BulkCustomerIdsRequest request) {
        long succeeded = 0;
        List<String> failedIds = new ArrayList<>();
        for (String customerId : request.customerIds()) {
            try {
                deleteCustomer(UUID.fromString(customerId));
                succeeded++;
            } catch (IllegalArgumentException | CustomerNotFoundException exception) {
                failedIds.add(customerId);
            }
        }
        return new BulkOperationResponse(
                new BulkOperationResponse.Data(request.customerIds().size(), succeeded, failedIds));
    }

    public byte[] exportCustomers(String search, CustomerStatus status, String sortBy, String sortDirection,
            String format) {
        List<CustomerRecord> filtered = customers.values().stream()
                .filter(customer -> matchesSearch(customer, search))
                .filter(customer -> status == null || customer.status == status)
                .collect(Collectors.toList());

        String csv = new StringBuilder()
                .append("id,companyName,domain,status,category,description\n")
                .append(filtered.stream()
                        .map(customer -> String.join(",",
                                customer.id.toString(),
                                escapeCsv(customer.companyName),
                                escapeCsv(customer.domain),
                                customer.status.name(),
                                escapeCsv(customer.category),
                                escapeCsv(customer.description)))
                        .collect(Collectors.joining("\n")))
                .toString();
        return csv.getBytes(StandardCharsets.UTF_8);
    }

    private CustomerRecord findCustomer(UUID customerId) {
        CustomerRecord customer = customers.get(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException(customerId.toString());
        }
        return customer;
    }

    private boolean matchesSearch(CustomerRecord customer, String search) {
        if (search == null || search.isBlank()) {
            return true;
        }
        String needle = search.toLowerCase();
        return customer.companyName.toLowerCase().contains(needle)
                || customer.domain.toLowerCase().contains(needle)
                || customer.category.toLowerCase().contains(needle)
                || customer.description.toLowerCase().contains(needle);
    }

    private CustomerTableItem toTableItem(CustomerRecord customer) {
        List<com.an.storemanagement.dto.customer.UserAvatar> preview = customer.users.stream()
                .limit(5)
                .map(user -> new com.an.storemanagement.dto.customer.UserAvatar(user.id, user.name, user.avatarUrl))
                .toList();
        return new CustomerTableItem(
                customer.id.toString(),
                new CompanySummary(customer.companyName, customer.domain, customer.logoUrl),
                customer.status,
                new CustomerAbout(customer.category, customer.description),
                new CustomerUsersPreview(customer.users.size(), preview,
                        Math.max(0, customer.users.size() - preview.size())));
    }

    private String escapeCsv(String value) {
        if (value == null) {
            return "";
        }
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return '"' + value.replace("\"", "\"\"") + '"';
        }
        return value;
    }

    private void seed() {
        customers.clear();
        customers.put(UUID.fromString("11111111-1111-1111-1111-111111111111"), new CustomerRecord(
                UUID.fromString("11111111-1111-1111-1111-111111111111"),
                "Framer",
                "framer.com",
                "https://logo.clearbit.com/framer.com",
                CustomerStatus.CUSTOMER,
                "Design Tools",
                "Make beautiful websites in minutes.",
                List.of(
                        new CustomerUserPreviewRecord("u_f1", "Alex", "https://i.pravatar.cc/150?u=f1"),
                        new CustomerUserPreviewRecord("u_f2", "Ben", "https://i.pravatar.cc/150?u=f2"),
                        new CustomerUserPreviewRecord("u_f3", "Clara", "https://i.pravatar.cc/150?u=f3"),
                        new CustomerUserPreviewRecord("u_f4", "Dan", "https://i.pravatar.cc/150?u=f4"),
                        new CustomerUserPreviewRecord("u_f5", "Eva", "https://i.pravatar.cc/150?u=f5"),
                        new CustomerUserPreviewRecord("u_f6", "Finn", "https://i.pravatar.cc/150?u=f6"),
                        new CustomerUserPreviewRecord("u_f7", "Gia", "https://i.pravatar.cc/150?u=f7"),
                        new CustomerUserPreviewRecord("u_f8", "Hank", "https://i.pravatar.cc/150?u=f8"),
                        new CustomerUserPreviewRecord("u_f9", "Ivy", "https://i.pravatar.cc/150?u=f9")),
                Instant.parse("2024-01-01T10:00:00Z"),
                Instant.parse("2024-06-01T10:00:00Z")));

        customers.put(UUID.fromString("22222222-2222-2222-2222-222222222222"), new CustomerRecord(
                UUID.fromString("22222222-2222-2222-2222-222222222222"),
                "Intercom",
                "intercom.com",
                "https://logo.clearbit.com/intercom.com",
                CustomerStatus.CUSTOMER,
                "Customer Engagement",
                "Build lasting customer relationships with Intercom.",
                List.of(
                        new CustomerUserPreviewRecord("u_i1", "Frank", "https://i.pravatar.cc/150?u=i1"),
                        new CustomerUserPreviewRecord("u_i2", "Grace", "https://i.pravatar.cc/150?u=i2"),
                        new CustomerUserPreviewRecord("u_i3", "Hannah", "https://i.pravatar.cc/150?u=i3"),
                        new CustomerUserPreviewRecord("u_i4", "Ian", "https://i.pravatar.cc/150?u=i4"),
                        new CustomerUserPreviewRecord("u_i5", "Jack", "https://i.pravatar.cc/150?u=i5"),
                        new CustomerUserPreviewRecord("u_i6", "Kara", "https://i.pravatar.cc/150?u=i6"),
                        new CustomerUserPreviewRecord("u_i7", "Leo", "https://i.pravatar.cc/150?u=i7")),
                Instant.parse("2024-02-01T10:00:00Z"),
                Instant.parse("2024-06-15T10:00:00Z")));

        customers.put(UUID.fromString("33333333-3333-3333-3333-333333333333"), new CustomerRecord(
                UUID.fromString("33333333-3333-3333-3333-333333333333"),
                "Linear",
                "linear.app",
                "https://logo.clearbit.com/linear.app",
                CustomerStatus.CUSTOMER,
                "Developer Tools",
                "The issue tracking tool you will enjoy using.",
                List.of(
                        new CustomerUserPreviewRecord("u_l1", "Mia", "https://i.pravatar.cc/150?u=l1"),
                        new CustomerUserPreviewRecord("u_l2", "Nina", "https://i.pravatar.cc/150?u=l2"),
                        new CustomerUserPreviewRecord("u_l3", "Owen", "https://i.pravatar.cc/150?u=l3")),
                Instant.parse("2024-03-01T10:00:00Z"),
                Instant.parse("2024-06-20T10:00:00Z")));
    }

    private record CustomerRecord(
            UUID id,
            String companyName,
            String domain,
            String logoUrl,
            CustomerStatus status,
            String category,
            String description,
            List<CustomerUserPreviewRecord> users,
            Instant createdAt,
            Instant updatedAt) {

        CustomerRecord withUpdates(UpdateCustomerRequest request) {
            return new CustomerRecord(
                    id,
                    request.companyName() == null ? companyName : request.companyName(),
                    request.domain() == null ? domain : request.domain(),
                    request.logoUrl() == null ? logoUrl : request.logoUrl(),
                    request.status() == null ? status : request.status(),
                    request.category() == null ? category : request.category(),
                    request.description() == null ? description : request.description(),
                    users,
                    createdAt,
                    Instant.now());
        }
    }

    private record CustomerUserPreviewRecord(String id, String name, String avatarUrl) {
    }
}