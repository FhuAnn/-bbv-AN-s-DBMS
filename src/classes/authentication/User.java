package classes.authentication;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class User {
    private final AuthService authService = new AuthService();
    public String userID;
    public String username;
    public String email;
    public String hashedPassword;

    public User createNewUser(String username, String email, String rawPassword) {
        this.userID = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.hashedPassword = authService.hashPassword(rawPassword);
        return this;
    }

    public User getUserData(String username) {
        return Objects.equals(this.username, username) ? this : null;
    }

    public Boolean checkUserExist(String username) {
        return Objects.equals(this.username, username);
    }

    public Boolean updateUserInfo() {
        return true;
    }

    public Boolean updateUserInfo(Map<String, String> updatedFields) {
        if (updatedFields == null || updatedFields.isEmpty()) {
            return false;
        }

        if (updatedFields.containsKey("username")) {
            this.username = updatedFields.get("username");
        }
        if (updatedFields.containsKey("email")) {
            this.email = updatedFields.get("email");
        }
        if (updatedFields.containsKey("hashedPassword")) {
            this.hashedPassword = updatedFields.get("hashedPassword");
        }
        return true;
    }
}
