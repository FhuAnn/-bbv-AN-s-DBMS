import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import classes.authentication.AuthService;
import classes.authentication.User;
import classes.authentication.TokenSet;

public class AuthServiceTest {
    @Test
    public void testHashPassword() {
        AuthService authService = new AuthService();
        assertNotEquals("hophamphuan123", authService.hashPassword("hophamphuan123"));
    }
    
    @Test
    public void testVerifyPassword() {
        AuthService authService = new AuthService();
        assertTrue(authService.verifyPassword("hophamphuan123", authService.hashPassword("hophamphuan123")));
    }

    @Test
    public void testGenerateAndVerifyToken() {
        AuthService authService = new AuthService();
        TokenSet tokenSet = authService.generateAuthAndRefreshToken("u1");

        assertNotEquals(tokenSet.authToken, tokenSet.refreshToken);
        assertTrue(authService.verifyToken(tokenSet));
    }

    @Test
    public void testVerifyTokenWithNullTokenSet() {
        AuthService authService = new AuthService();

        assertFalse(authService.verifyToken(null));
    }

    @Test
    public void testValidateRefreshToken() {
        AuthService authService = new AuthService();
        TokenSet tokenSet = authService.generateAuthAndRefreshToken("u1");

        assertTrue(authService.verifyToken(tokenSet));
        assertTrue("u1".equals(authService.validateRefreshToken(tokenSet.refreshToken)));
        assertNull(authService.validateRefreshToken("invalid-refresh-token"));
    }

    @Test
    public void testRegisterAndLoginFlow() {
        AuthService authService = new AuthService();

        User user = authService.register("alice", "alice@example.com", "secret123");
        assertNotNull(user);
        assertTrue(user.checkUserExist("alice"));

        TokenSet tokenSet = authService.login("alice", "secret123");

        assertNotNull(tokenSet);
        assertTrue(authService.verifyToken(tokenSet));
        assertEquals(user.userID, authService.validateRefreshToken(tokenSet.refreshToken));
    }

    @Test
    public void testLoginShouldRejectWrongPassword() {
        AuthService authService = new AuthService();
        authService.register("alice", "alice@example.com", "secret123");

        assertThrows(SecurityException.class, () -> authService.login("alice", "wrong-password"));
    }

    @Test
    public void testLogoutShouldRevokeActiveSession() {
        AuthService authService = new AuthService();
        authService.register("alice", "alice@example.com", "secret123");
        TokenSet tokenSet = authService.login("alice", "secret123");

        authService.logout(tokenSet.authToken);

        assertFalse(authService.verifyToken(tokenSet));
        assertNull(authService.validateRefreshToken(tokenSet.refreshToken));
    }

    @Test
    public void testForgotPasswordAndResetPasswordFlow() {
        AuthService authService = new AuthService();
        authService.register("alice", "alice@example.com", "secret123");
        TokenSet tokenSet = authService.login("alice", "secret123");

        String resetToken = authService.forgotPassword("alice@example.com");

        assertNotNull(resetToken);

        authService.resetPassword(resetToken, "newSecret123");

        assertFalse(authService.verifyToken(tokenSet));
        assertThrows(SecurityException.class, () -> authService.login("alice", "secret123"));
        TokenSet newTokenSet = authService.login("alice", "newSecret123");
        assertNotNull(newTokenSet);
        assertTrue(authService.verifyToken(newTokenSet));
    }

    @Test
    public void testForgotPasswordShouldRejectUnknownEmail() {
        AuthService authService = new AuthService();

        assertThrows(SecurityException.class, () -> authService.forgotPassword("missing@example.com"));
    }

    @Test
    public void testResetPasswordShouldRejectInvalidResetToken() {
        AuthService authService = new AuthService();
        authService.register("alice", "alice@example.com", "secret123");

        assertThrows(SecurityException.class, () -> authService.resetPassword("bad-reset-token", "newSecret123"));
    }
}
