package classes.storageengine;

import java.time.Instant;
import java.util.UUID;

import enums.LogRecordType;

public class CommitLogRecord extends LogRecord {

    public CommitLogRecord() {
        // TODO: Implement
    }

    public CommitLogRecord(
            long lsn,
            UUID transactionId,
            Instant createdAt) {
        super(lsn, transactionId, createdAt);

        // TODO: Implement
    }

    @Override
    public LogRecordType getType() {
        return null;
    }
}
