package classes.tx.command;

import java.util.UUID;

import classes.tx.TransactionManager;
import enums.transaction.TransactionCommandType;

public class CreateSavepointCommand extends AbstractTransactionCommand {
    private String savepointName;

    public CreateSavepointCommand(
            TransactionManager transactionManager,
            UUID transactionId,
            String savepointName) {
        super(transactionManager, transactionId);

        // TODO: Implement
    }

    @Override
    public TransactionCommandResult execute() {
        // TODO: Implement
        return null;
    }

    @Override
    public TransactionCommandType getType() {
        // TODO: Implement
        return null;
    }

    public String getSavepointName() {
        // TODO: Implement
        return null;
    }
}
