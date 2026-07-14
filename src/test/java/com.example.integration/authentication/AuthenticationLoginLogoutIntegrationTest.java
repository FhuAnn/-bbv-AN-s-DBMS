
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import classes.authentication.AuthService;
import classes.authentication.TokenSet;
import classes.authentication.User;

public class AuthenticationLoginLogoutIntegrationTest {
    @Test
    public void loginAndLogoutShouldWorkEndToEnd() {
        AuthService authService = new AuthService();

        User user = authService.register("bob", "bob@example.com", "secret123");
        TokenSet tokenSet = authService.login("bob", "secret123");

        assertNotNull(user);
        assertNotNull(tokenSet);
        assertTrue(authService.verifyToken(tokenSet));

        authService.logout(tokenSet.authToken);

        assertFalse(authService.verifyToken(tokenSet));
    }
}