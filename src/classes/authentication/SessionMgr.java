package classes.authentication;

public class SessionMgr {
    private String latestAuthToken;

    public TokenSet createSession(String userId, String ip, String device) {
        return null;
    }

    public boolean validateSession(String authToken) {
        return false;
    }

    public TokenSet refreshSession(String refreshToken) {
        return null;
    }

    public String validateRefreshToken(String refreshToken) {
        return null;
    }

    public void revokeSession(String authToken) {
    }

    public void revokeSessionsByUserId(String userId) {
    }

    public String latestAuthToken() {
        return latestAuthToken;
    }
}
