package classes.authentication;

public class SecurityManager {
    public UserSession authenticate(Credentials cred) {
        return null;
    }

    public boolean authorize(UserSession session, Permission perm) {
        return false;
    }
}