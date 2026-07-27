package classes.tx.command;

import java.util.UUID;

import classes.tx.Transaction;
import enums.transaction.TransactionCommandStatus;
import enums.transaction.TransactionCommandType;

public class TransactionCommandResult {
    private boolean successful;
    private UUID transactionId;
    private TransactionCommandType commandType;
    private TransactionCommandStatus status;
    private String message;
    private Transaction transaction;

    public TransactionCommandResult() {
        // TODO: Implement
    }

    public TransactionCommandResult(
            boolean successful,
            UUID transactionId,
            TransactionCommandType commandType,
            TransactionCommandStatus status,
            String message,
            Transaction transaction) {
        // TODO: Implement
    }

    public boolean isSuccessful() {
        // TODO: Implement
        return false;
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

    public String getMessage() {
        // TODO: Implement
        return null;
    }

    public Transaction getTransaction() {
        // TODO: Implement
        return null;
    }
}
