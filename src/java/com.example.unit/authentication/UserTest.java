package authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import classes.authentication.AuthService;
import classes.authentication.User;

public class UserTest {
    @Test
    public void createNewUserShouldPopulateFieldsAndHashPassword() {
        User template = new User();

        User user = template.createNewUser("alice", "alice@example.com", "secret123");

        assertNotNull(user.userID);
        assertEquals("alice", user.username);
        assertEquals("alice@example.com", user.email);
        assertNotEquals("secret123", user.hashedPassword);
        assertTrue(new AuthService().verifyPassword("secret123", user.hashedPassword));
    }

    @Test
    public void checkUserExistShouldMatchUsername() {
        User user = new User();
        user.username = "alice";

        assertTrue(user.checkUserExist("alice"));
    }

    @Test
    public void getUserDataCurrentlyReturnsSameInstance() {
        User user = new User();
        user.username = "alice";

        assertSame(user, user.getUserData("alice"));
    }

    @Test
    public void updateUserInfoReturnsTrue() {
        User user = new User();

        assertTrue(user.updateUserInfo());
    }

    @Test
    public void updateUserInfoWithFieldsShouldUpdateProfileData() {
        User user = new User();
        user.username = "alice";
        user.email = "alice@example.com";

        Map<String, String> updatedFields = new HashMap<>();
        updatedFields.put("username", "alice2");
        updatedFields.put("email", "alice2@example.com");

        assertTrue(user.updateUserInfo(updatedFields));
        assertEquals("alice2", user.username);
        assertEquals("alice2@example.com", user.email);
    }

    @Test
    public void updateUserInfoWithEmptyFieldsShouldReturnFalse() {
        User user = new User();

        assertFalse(user.updateUserInfo(Map.of()));
    }
}