package classes.storageengine;

import java.time.Instant;
import java.util.UUID;

import enums.LogRecordType;

public class LogRecordDefinition {
    private long lsn;
    private UUID transactionId;
    private Instant createdAt;
    private LogRecordType type;
    private String fileName;
    private RecordId recordId;
    private byte[] beforeImage;
    private byte[] afterImage;

    public LogRecordDefinition() {
        // TODO: Implement
    }

    public long getLsn() {
        return 0L;
    }

    public UUID getTransactionId() {
        return null;
    }

    public Instant getCreatedAt() {
        return null;
    }

    public LogRecordType getType() {
        return null;
    }

    public String getFileName() {
        return null;
    }

    public RecordId getRecordId() {
        return null;
    }

    public byte[] getBeforeImage() {
        return null;
    }

    public byte[] getAfterImage() {
        return null;
    }
}
