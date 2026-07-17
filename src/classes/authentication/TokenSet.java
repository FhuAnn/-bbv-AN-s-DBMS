package classes.authentication;

import java.time.Instant;

public class TokenSet {
    public String sessionId;
    public String userId;
    public String authToken;
    public String refreshToken;
    public Instant createdAt;
    public Instant expiresAt;

    public TokenSet(String sessionId, String userId, String authToken, String refreshToken, Instant createdAt, Instant expiresAt) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.authToken = authToken;
        this.refreshToken = refreshToken;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }
}
