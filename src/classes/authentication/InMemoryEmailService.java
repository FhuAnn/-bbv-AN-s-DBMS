package classes.authentication;

import java.util.HashMap;
import java.util.Map;

public class InMemoryEmailService implements EmailService {
    private final Map<String, VerificationEmail> verificationEmailsByUserId = new HashMap<>();

    @Override
    public void sendVerificationEmail(String userId, String email) {
        verificationEmailsByUserId.put(userId, new VerificationEmail(userId, email));
    }

    @Override
    public boolean hasVerificationEmailFor(String email) {
        for (VerificationEmail verificationEmail : verificationEmailsByUserId.values()) {
            if (verificationEmail.email.equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public VerificationEmail getVerificationEmailForUserId(String userId) {
        return verificationEmailsByUserId.get(userId);
    }
}