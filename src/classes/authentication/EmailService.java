package classes.authentication;

public interface EmailService {
    final class VerificationEmail {
        public final String userId;
        public final String email;

        public VerificationEmail(String userId, String email) {
            this.userId = userId;
            this.email = email;
        }
    }

    void sendVerificationEmail(String userId, String email);

    boolean hasVerificationEmailFor(String email);

    VerificationEmail getVerificationEmailForUserId(String userId);
}