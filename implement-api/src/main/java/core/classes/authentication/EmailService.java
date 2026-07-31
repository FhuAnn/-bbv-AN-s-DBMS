package core.classes.authentication;

public interface EmailService {
    void sendVerificationEmail(String userId, String email);
}
