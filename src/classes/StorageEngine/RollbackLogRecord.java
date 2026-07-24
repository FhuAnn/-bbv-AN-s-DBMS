package classes.storageengine;

import java.time.Instant;
import java.util.UUID;

import enums.LogRecordType;

public class RollbackLogRecord extends LogRecord {

    public RollbackLogRecord() {
        // TODO: Implement
    }

    public RollbackLogRecord(
            long lsn,
            UUID transactionId,
            Instant createdAt
    ) {
        super(lsn, transactionId, createdAt);

        // TODO: Implement
    }

    @Override
    public LogRecordType getType() {
        return null;
    }
}
