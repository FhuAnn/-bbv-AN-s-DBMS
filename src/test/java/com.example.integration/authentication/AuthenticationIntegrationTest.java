
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import classes.authentication.AuthService;
import classes.authentication.EmailService;
import classes.authentication.TokenSet;
import classes.authentication.User;

public class AuthenticationIntegrationTest {
    @Test
    public void registerLoginLogoutAndResetPasswordShouldWorkTogether() {
        AuthService authService = new AuthService();

        User user = authService.register("alice", "alice@example.com", "secret123");
        EmailService.VerificationEmail verificationEmail = authService.getEmailService().getVerificationEmailForUserId(user.userID);

        assertNotNull(user);
        assertNotNull(verificationEmail);
        assertEquals("alice@example.com", verificationEmail.email);
        assertTrue(authService.getEmailService().hasVerificationEmailFor("alice@example.com"));

        TokenSet tokenSet = authService.login("alice", "secret123");
        assertNotNull(tokenSet);
        assertTrue(authService.verifyToken(tokenSet));

        authService.logout(tokenSet.authToken);
        assertFalse(authService.verifyToken(tokenSet));

        String resetToken = authService.forgotPassword("alice@example.com");
        assertNotNull(resetToken);

        authService.resetPassword(resetToken, "newSecret123");

        assertThrows(SecurityException.class, () -> authService.login("alice", "secret123"));
        TokenSet newTokenSet = authService.login("alice", "newSecret123");
        assertTrue(authService.verifyToken(newTokenSet));
    }
}