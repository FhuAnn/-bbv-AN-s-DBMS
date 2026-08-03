package com.an.storemanagement.service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final Set<String> revokedTokens = ConcurrentHashMap.newKeySet();

    public void logout(String userId) {
        revokedTokens.add(userId);
    }

    public boolean isLoggedOut(String userId) {
        return revokedTokens.contains(userId);
    }
}