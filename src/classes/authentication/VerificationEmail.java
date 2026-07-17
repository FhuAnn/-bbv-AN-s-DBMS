package classes.authentication;

import java.time.Instant;

public class VerificationEmail {
    public String userId;
    public String email;
    public Instant sentAt;

    public VerificationEmail() {
    }

    public VerificationEmail(String userId, String email, Instant sentAt) {
        this.userId = userId;
        this.email = email;
        this.sentAt = sentAt;
    }
}
