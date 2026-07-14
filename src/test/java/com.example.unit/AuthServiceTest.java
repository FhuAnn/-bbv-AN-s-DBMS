import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import classes.authentication.AuthService;
import classes.authentication.TokenSet;

public class AuthServiceTest {
    private final AuthService authService = new AuthService();
    @Test
    public void testHashPassword() {
        assertNotEquals("hophamphuan123", authService.hashPassword("hophamphuan123"));
    }
    
    @Test
    public void testVerifyPassword() {
        assertTrue(authService.verifyPassword("hophamphuan123", authService.hashPassword("hophamphuan123")));
    }

    @Test
    public void testGenerateAndVerifyToken() {
        TokenSet tokenSet = authService.generateAuthAndRefreshToken("u1");

        assertNotEquals(tokenSet.authToken, tokenSet.refreshToken);
        assertTrue(authService.verifyToken(tokenSet));
    }

}
