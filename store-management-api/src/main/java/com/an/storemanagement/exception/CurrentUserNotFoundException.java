package com.an.storemanagement.exception;
public class CurrentUserNotFoundException extends RuntimeException {

    public CurrentUserNotFoundException(String userId) {
        super("Current user not found: " + userId);
    }
}
