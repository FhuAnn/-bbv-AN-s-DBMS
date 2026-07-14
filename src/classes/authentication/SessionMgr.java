package classes.authentication;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionMgr {
    public final Map<String, SessionContext> activeSessions = new HashMap<>();
    private final Map<String, String> refreshTokenIndex = new HashMap<>();
    private String latestAuthToken;

    public TokenSet createSession(String userId, String ip, String device) {
        Instant now = Instant.now();
        String authToken = UUID.randomUUID().toString();
        String refreshToken = UUID.randomUUID().toString();
        String sessionId = UUID.randomUUID().toString();
        SessionContext context = new SessionContext(sessionId, userId, authToken, refreshToken, ip, device, now, now.plusSeconds(3600));
        activeSessions.put(authToken, context);
        refreshTokenIndex.put(refreshToken, authToken);
        latestAuthToken = authToken;
        return new TokenSet(sessionId, userId, authToken, refreshToken, now, context.expiresAt);
    }

    public boolean validateSession(String authToken) {
        SessionContext context = activeSessions.get(authToken);
        if (context == null) {
            return false;
        }
        if (!context.expiresAt.isAfter(Instant.now())) {
            activeSessions.remove(authToken);
            refreshTokenIndex.remove(context.refreshToken);
            return false;
        }
        context.lastActiveAt = Instant.now();
        return true;
    }

    public TokenSet refreshSession(String refreshToken) {
        String authToken = refreshTokenIndex.get(refreshToken);
        if (authToken == null) {
            throw new SecurityException("Invalid refresh token");
        }

        SessionContext old = activeSessions.remove(authToken);
        refreshTokenIndex.remove(refreshToken);
        if (old == null) {
            throw new SecurityException("No active session to refresh");
        }

        latestAuthToken = null;
        return createSession(old.userId, old.ipAddress, old.deviceType);
    }

    public String validateRefreshToken(String refreshToken) {
        String authToken = refreshTokenIndex.get(refreshToken);
        if (authToken == null) {
            return null;
        }

        SessionContext context = activeSessions.get(authToken);
        if (context == null || !context.expiresAt.isAfter(Instant.now())) {
            refreshTokenIndex.remove(refreshToken);
            if (context != null) {
                activeSessions.remove(authToken);
            }
            return null;
        }

        return context.userId;
    }

    public void revokeSession(String authToken) {
        SessionContext removed = activeSessions.remove(authToken);
        if (removed != null) {
            refreshTokenIndex.remove(removed.refreshToken);
        }
        if (authToken != null && authToken.equals(latestAuthToken)) {
            latestAuthToken = null;
        }
    }

    public String latestAuthToken() {
        return latestAuthToken;
    }
}
