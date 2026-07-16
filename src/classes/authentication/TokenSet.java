package classes.authentication;

import java.time.Instant;

public class TokenSet {
    public final String sessionId;
    public final String userId;
    public final String authToken;
    public final String refreshToken;
    public final Instant createdAt;
    public final Instant expiresAt;

    public TokenSet(String sessionId, String userId, String authToken, String refreshToken, Instant createdAt, Instant expiresAt) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.authToken = authToken;
        this.refreshToken = refreshToken;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }
}
