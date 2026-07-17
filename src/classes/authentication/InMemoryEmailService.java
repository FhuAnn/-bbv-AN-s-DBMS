package classes.authentication;

public class InMemoryEmailService implements EmailService {
    @Override
    public void sendVerificationEmail(String userId, String email) {
    }

    public boolean hasVerificationEmailFor(String email) {
        return false;
    }

    public VerificationEmail getVerificationEmailForUserId(String userId) {
        return null;
    }
}
