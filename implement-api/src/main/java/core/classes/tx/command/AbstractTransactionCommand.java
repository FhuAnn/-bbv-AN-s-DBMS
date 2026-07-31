package core.classes.tx.command;

import java.time.Instant;
import java.util.UUID;

import classes.tx.TransactionManager;
import interfaces.tx.ITransactionCommand;

public abstract class AbstractTransactionCommand
        implements ITransactionCommand {

    protected UUID commandId;
    protected UUID transactionId;
    protected TransactionManager transactionManager;
    protected Instant createdAt;

    protected AbstractTransactionCommand(
            TransactionManager transactionManager,
            UUID transactionId) {
        // TODO: Implement
    }

    public UUID getCommandId() {
        // TODO: Implement
        return null;
    }

    @Override
    public UUID getTransactionId() {
        // TODO: Implement
        return null;
    }

    public TransactionManager getTransactionManager() {
        // TODO: Implement
        return null;
    }

    public Instant getCreatedAt() {
        // TODO: Implement
        return null;
    }
}
