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
        return null;
    }

    public User getUserData(String username) {
        return null;
    }

    public Boolean checkUserExist(String username) {
        return null;
    }

    public Boolean updateUserInfo() {
        return null;
    }

    public Boolean updateUserInfo(Map<String, String> updatedFields) {
        return null;
    }
}
