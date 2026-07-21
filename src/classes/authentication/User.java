package classes.authentication;

import java.util.Map;

public class User {
    public String userID;
    public String username;
    public String email;
    public String hashedPassword;

    public User() {

    }

    public User(String username, String email, String hashedPassword) {
        this.username = username;
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    public User createNewUser(String username, String email, String rawPassword) {
        return null;
    }

    public User getUserData(String username) {
        return null;
    }

    public Boolean checkUserExist(String username) {
        return Boolean.FALSE;
    }

    public Boolean updateUserInfo() {
        return Boolean.FALSE;
    }

    public Boolean updateUserInfo(Map updatedFields) {
        return Boolean.FALSE;
    }
}
