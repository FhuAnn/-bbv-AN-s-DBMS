package classes.authentication;

import java.time.Instant;

public class UserRole {
    public
    final String userId;
public final String roleId;
    public final Instant createdAt;
   public final String ownerId;

    public UserRole(String userId, String roleId, String ownerId) {
        this.userId = userId;
        this.roleId = roleId;
        this.ownerId = ownerId;
        this.createdAt = Instant.now();
    }
}
