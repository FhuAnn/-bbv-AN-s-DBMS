package classes.authentication;

import java.time.Instant;
import java.util.UUID;

public class UserSession {
    public UUID userId;
    public Instant expiry;
}
