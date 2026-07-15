package classes.authentication;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class AuditLog {
    final String logId = UUID.randomUUID().toString();
    final String actorID;
    final String action;
    final String targetId;
    final String timestamp;
    final String ipAddress;

    public AuditLog(String actorID, String action, String targetId, String ipAddress) {
        this.actorID = actorID;
        this.action = action;
        this.targetId = targetId;
    this.timestamp = Instant.now().toString();
        this.ipAddress = ipAddress;
}

    public void logging(String info) {
    }

    List<AuditLog> getLogging(Object options) {
        return List.of(this);
    }
}
