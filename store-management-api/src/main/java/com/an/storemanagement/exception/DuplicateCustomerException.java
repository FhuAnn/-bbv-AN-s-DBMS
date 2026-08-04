package com.an.storemanagement.exception;
public class DuplicateCustomerException extends RuntimeException {

    public DuplicateCustomerException(String domain) {
        super("Customer domain already exists: " + domain);
    }
}
