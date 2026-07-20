package classes.authentication;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class AuthService {
    private final User userStore = new User();
    private final Map<String, User> users = new LinkedHashMap<>();
    private final Map<String, String> sessions = new LinkedHashMap<>();

    public UUID register(String username, String password) {
        return null;
    }

    public String login(String username, String password) {
        return null;
    }

    public boolean logout(String token) {
        return false;
    }

    public boolean isAuthenticated(String token) {
        return false;

    }

    public void changePassword(String username, String oldPassword, String newPassword) {

    }

    public void disableUser(String username) {

    }

    public int getUserCount() {
        return users.size();
    }

    public int getSessionCount() {
        return sessions.size();
    }

    private String hash(String password) {
        return null;

    }

    private void validateUsername(String username) {

    }

    private void validatePassword(String password) {

    }

    private void validateToken(String token) {

    }
}
