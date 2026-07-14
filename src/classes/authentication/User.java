package classes.authentication;

import java.util.Objects;
import java.util.UUID;

public class User {
    private final AuthService authService = new AuthService();
    String userID;
    String username;
    String email;
    String hashedPassword;

    User createNewUser(String username, String email, String rawPassword) {
        User user = new User();
        user.userID = UUID.randomUUID().toString();
        user.username = username;
        user.email = email;
        user.hashedPassword = user.authService.hashPassword(rawPassword);
        return user;
    }

    User getUserData(String username) {
        return this;
    }

    Boolean checkUserExist(String username) {
        return Objects.equals(this.username, username);
    }

    Boolean updateUserInfo() {
        return true;
    }
}
