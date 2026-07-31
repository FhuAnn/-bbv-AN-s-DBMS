package core.classes.tx.command;

import java.time.Instant;
import java.util.UUID;

import core.enums.transaction.TransactionCommandStatus;
import core.enums.transaction.TransactionCommandType;

public class TransactionCommandRecord {

    private UUID commandId;
    private UUID transactionId;
    private TransactionCommandType commandType;
    private TransactionCommandStatus status;
    private Instant executedAt;
    private String message;

    public TransactionCommandRecord() {
        // TODO: Implement
    }

    public TransactionCommandRecord(
            UUID commandId,
            UUID transactionId,
            TransactionCommandType commandType,
            TransactionCommandStatus status,
            Instant executedAt,
            String message) {
        // TODO: Implement
    }

    public UUID getCommandId() {
        // TODO: Implement
        return null;
    }

    public UUID getTransactionId() {
        // TODO: Implement
        return null;
    }

    public TransactionCommandType getCommandType() {
        // TODO: Implement
        return null;
    }

    public TransactionCommandStatus getStatus() {
        // TODO: Implement
        return null;
    }

    public Instant getExecutedAt() {
        // TODO: Implement
        return null;
    }

    public String getMessage() {
        // TODO: Implement
        return null;
    }
}
