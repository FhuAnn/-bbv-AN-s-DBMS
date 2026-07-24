package classes.storageengine;

import java.time.Instant;
import java.util.UUID;

import enums.LogRecordType;

public abstract class LogRecord {
    private long lsn;
    private UUID transactionId;
    private Instant createdAt;

    protected LogRecord() {
        // TODO: Implement
    }

    protected LogRecord(
            long lsn,
            UUID transactionId,
            Instant createdAt) {
        // TODO: Implement
    }

    public abstract LogRecordType getType();

    public long getLsn() {
        return 0L;
    }

    public UUID getTransactionId() {
        return null;
    }

    public Instant getCreatedAt() {
        return null;
    }
}
