package classes.authentication;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserSession {
    public UUID userId;
    public Set<Role> roles = new HashSet<>();
    public Instant expiry;
}