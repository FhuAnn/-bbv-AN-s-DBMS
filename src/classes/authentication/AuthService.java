package classes.authentication;



import java.nio.charset.StandardCharsets;

import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;

import java.util.HashMap;

import java.util.Map;

import java.util.UUID;



public class AuthService {

    private final SessionMgr sessionMgr = new SessionMgr();

    private final EmailService emailService = new InMemoryEmailService();

    private final Map<String, User> usersByUsername = new HashMap<>();

    private final Map<String, User> usersByEmail = new HashMap<>();

    private final Map<String, String> resetTokens = new HashMap<>();



    public String hashPassword(String rawPassword) {

        return null;

    }



    public boolean verifyPassword(String raw, String hashed) {

        return false;

    }



    public User register(String username, String email, String rawPassword) {

        return null;

    }



    public TokenSet login(String username, String password) {

        return null;

    }



    public void logout(String authToken) {

    }



    public String forgotPassword(String email) {

        return null;

    }



    public void resetPassword(String resetToken, String newPassword) {

    }



    public TokenSet generateAuthAndRefreshToken(String userId) {

        return null;

    }



    public boolean verifyToken(TokenSet authToken) {

        return false;

    }



    public String validateRefreshToken(String refreshToken) {

        return null;

    }



    public EmailService getEmailService() {

        return null;
    }

}

