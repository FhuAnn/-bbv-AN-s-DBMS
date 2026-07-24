package classes.storageengine;

import java.time.Instant;
import java.util.UUID;

import enums.LogRecordType;

public class DeleteLogRecord extends LogRecord {

    private String fileName;
    private RecordId recordId;
    private byte[] beforeImage;

    public DeleteLogRecord() {
        // TODO: Implement
    }

    public DeleteLogRecord(
            long lsn,
            UUID transactionId,
            Instant createdAt,
            String fileName,
            RecordId recordId,
            byte[] beforeImage) {
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

    public byte[] getBeforeImage() {
        return null;
    }
}
