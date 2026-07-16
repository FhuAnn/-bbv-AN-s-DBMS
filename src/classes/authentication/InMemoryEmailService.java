package classes.authentication;



import java.util.HashMap;

import java.util.Map;



public class InMemoryEmailService implements EmailService {

    private final Map<String, VerificationEmail> verificationEmailsByUserId = new HashMap<>();



    @Override

    public void sendVerificationEmail(String userId, String email) {

    }



    @Override

    public boolean hasVerificationEmailFor(String email) {

        return false;

    }



    @Override

    public VerificationEmail getVerificationEmailForUserId(String userId) {

        return null;

    }

}