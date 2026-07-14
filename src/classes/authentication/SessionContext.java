package classes.authentication;

import java.time.Instant;

public class SessionContext {
    public final String sessionId;
    public final String userId;
    public final String authToken;
    public final String refreshToken;
    public final String ipAddress;
    public final String deviceType;
    public final Instant createdAt;
    public final Instant expiresAt;
    public Instant lastActiveAt;

    public SessionContext(String sessionId, String userId, String authToken, String refreshToken, String ipAddress, String deviceType, Instant createdAt, Instant expiresAt) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.authToken = authToken;
        this.refreshToken = refreshToken;
        this.ipAddress = ipAddress;
        this.deviceType = deviceType;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.lastActiveAt = createdAt;
    }
}
