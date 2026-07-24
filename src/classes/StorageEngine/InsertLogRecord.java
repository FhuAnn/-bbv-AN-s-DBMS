package classes.storageengine;

import java.time.Instant;
import java.util.UUID;

import enums.LogRecordType;

public class InsertLogRecord extends LogRecord {
    private String fileName;
    private RecordId recordId;
    private byte[] afterImage;

    public InsertLogRecord() {
        // TODO: Implement
    }

    public InsertLogRecord(
            long lsn,
            UUID transactionId,
            Instant createdAt,
            String fileName,
            RecordId recordId,
            byte[] afterImage) {
        super(lsn, transactionId, createdAt);

        // TODO: Implement
    }

    @Override
    public LogRecordType getType() {
        return null;
    }

    public String getFileName() {
        return null;
    }

    public RecordId getRecordId() {
        return null;
    }

    public byte[] getAfterImage() {
        return null;
    }
}
