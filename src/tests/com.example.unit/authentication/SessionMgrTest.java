import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import classes.authentication.SessionMgr;
import classes.authentication.TokenSet;

public class SessionMgrTest {
    @Test
    public void createSessionShouldStoreActiveSession() {
        SessionMgr sessionMgr = new SessionMgr();

        TokenSet tokenSet = sessionMgr.createSession("u1", "127.0.0.1", "dev1");

        assertNotNull(tokenSet.authToken);
        assertNotNull(tokenSet.refreshToken);
        assertNotEquals(tokenSet.authToken, tokenSet.refreshToken);
        assertTrue(sessionMgr.validateSession(tokenSet.authToken));
        assertEquals("u1", sessionMgr.validateRefreshToken(tokenSet.refreshToken));
        assertEquals(tokenSet.authToken, sessionMgr.latestAuthToken());
    }

    @Test
    public void validateSessionShouldReturnFalseForUnknownToken() {
        SessionMgr sessionMgr = new SessionMgr();

        assertFalse(sessionMgr.validateSession("missing-token"));
    }

    @Test
    public void revokeSessionShouldRemoveActiveAndRefreshTokens() {
        SessionMgr sessionMgr = new SessionMgr();
        TokenSet tokenSet = sessionMgr.createSession("u1", "127.0.0.1", "dev1");

        sessionMgr.revokeSession(tokenSet.authToken);

        assertFalse(sessionMgr.validateSession(tokenSet.authToken));
        assertNull(sessionMgr.validateRefreshToken(tokenSet.refreshToken));
        assertNull(sessionMgr.latestAuthToken());
    }

    @Test
    public void refreshSessionShouldCreateNewTokenSet() {
        SessionMgr sessionMgr = new SessionMgr();
        TokenSet tokenSet = sessionMgr.createSession("u1", "127.0.0.1", "dev1");

        TokenSet refreshed = sessionMgr.refreshSession(tokenSet.refreshToken);

        assertEquals("u1", refreshed.userId);
        assertNotNull(refreshed.authToken);
        assertNotEquals(tokenSet.authToken, refreshed.authToken);
        assertFalse(sessionMgr.validateSession(tokenSet.authToken));
        assertTrue(sessionMgr.validateSession(refreshed.authToken));
    }

    @Test
    public void refreshSessionShouldRejectInvalidToken() {
        SessionMgr sessionMgr = new SessionMgr();

        assertThrows(SecurityException.class, () -> sessionMgr.refreshSession("bad-token"));
    }
}