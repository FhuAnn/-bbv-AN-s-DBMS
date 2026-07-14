package classes.authentication;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthService {
    private final SessionMgr sessionMgr = new SessionMgr();
    private final EmailService emailService = new InMemoryEmailService();
    private final Map<String, User> usersByUsername = new HashMap<>();
    private final Map<String, User> usersByEmail = new HashMap<>();
    private final Map<String, String> resetTokens = new HashMap<>();

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

    public User register(String username, String email, String rawPassword) {
        if (username == null || email == null || rawPassword == null) {
            throw new IllegalArgumentException("Username, email, and password are required");
        }

        if (usersByUsername.containsKey(username)) {
            throw new SecurityException("User already exists: " + username);
        }

        User user = new User().createNewUser(username, email, rawPassword);
        usersByUsername.put(username, user);
        usersByEmail.put(email, user);
        emailService.sendVerificationEmail(user.userID, email);
        return user;
    }

    public TokenSet login(String username, String password) {
        User user = usersByUsername.get(username);
        if (user == null || !verifyPassword(password, user.hashedPassword)) {
            throw new SecurityException("Invalid credentials");
        }

        return generateAuthAndRefreshToken(user.userID);
    }

    public void logout(String authToken) {
        sessionMgr.revokeSession(authToken);
    }

    public String forgotPassword(String email) {
        User user = usersByEmail.get(email);
        if (user == null) {
            throw new SecurityException("User not found for email: " + email);
        }

        String resetToken = UUID.randomUUID().toString();
        resetTokens.put(resetToken, user.userID);
        return resetToken;
    }

    public void resetPassword(String resetToken, String newPassword) {
        if (resetToken == null || newPassword == null) {
            throw new IllegalArgumentException("Reset token and new password are required");
        }

        String userId = resetTokens.remove(resetToken);
        if (userId == null) {
            throw new SecurityException("Invalid reset token");
        }

        User user = usersByUsername.values().stream()
                .filter(item -> userId.equals(item.userID))
                .findFirst()
                .orElseThrow(() -> new SecurityException("User not found for reset token"));

        user.hashedPassword = hashPassword(newPassword);
        sessionMgr.revokeSessionsByUserId(userId);
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

    public EmailService getEmailService() {
        return emailService;
    }
}
