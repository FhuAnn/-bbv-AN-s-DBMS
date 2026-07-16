package classes.authentication;

import java.time.Instant;

public class SecurityEvent {
    public String message;
    public Instant timestamp;

    public SecurityEvent(String message) {
        this.message = message;
        this.timestamp = Instant.now();
    }
}