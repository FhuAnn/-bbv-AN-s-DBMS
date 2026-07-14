package classes.authentication;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthService {
    private final SessionMgr sessionMgr = new SessionMgr();

    public String hashPassword(String rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder(hashedBytes.length * 2);
            for (byte hashedByte : hashedBytes) {
                builder.append(String.format("%02x", hashedByte));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("SHA-256 is not available", exception);
        }
    }

    public boolean verifyPassword(String raw, String hashed) {
        if (raw == null || hashed == null) {
            return false;
        }
        return hashPassword(raw).equals(hashed);
    }

    public TokenSet generateAuthAndRefreshToken(String userId) {
        return sessionMgr.createSession(userId, "unknown", "unknown");
    }

    public boolean verifyToken(TokenSet authToken) {
        return authToken != null && sessionMgr.validateSession(authToken.authToken);
    }

    public String validateRefreshToken(String refreshToken) {
        return sessionMgr.validateRefreshToken(refreshToken);
    }
}
