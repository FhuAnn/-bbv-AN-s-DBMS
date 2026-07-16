package classes.authentication;



import java.util.ArrayList;

import java.time.Instant;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

import java.util.UUID;



public class SessionMgr {

    public final Map<String, SessionContext> activeSessions = new HashMap<>();

    private final Map<String, String> refreshTokenIndex = new HashMap<>();

    private String latestAuthToken;



    public TokenSet createSession(String userId, String ip, String device) {

        return null;

    }



    public boolean validateSession(String authToken) {

        return false;

    }



    public TokenSet refreshSession(String refreshToken) {

        return null;

    }



    public String validateRefreshToken(String refreshToken) {

        return null;

    }



    public void revokeSession(String authToken) {

    }



    public void revokeSessionsByUserId(String userId) {

    }



    public String latestAuthToken() {

        return null;

    }

}

