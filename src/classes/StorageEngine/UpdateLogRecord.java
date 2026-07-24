package classes.storageengine;

import java.time.Instant;
import java.util.UUID;

import enums.LogRecordType;

public class UpdateLogRecord extends LogRecord {

    private String fileName;
    private RecordId recordId;
    private byte[] beforeImage;
    private byte[] afterImage;

    public UpdateLogRecord() {
        // TODO: Implement
    }

    public UpdateLogRecord(
            long lsn,
            UUID transactionId,
            Instant createdAt,
            String fileName,
            RecordId recordId,
            byte[] beforeImage,
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

    public byte[] getBeforeImage() {
        return null;
    }

    public byte[] getAfterImage() {
        return null;
    }
}
