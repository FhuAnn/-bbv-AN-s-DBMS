package classes.tx.command;

import classes.tx.TransactionManager;
import enums.transaction.TransactionCommandType;

public class BeginTransactionCommand extends AbstractTransactionCommand {
    public BeginTransactionCommand(
            TransactionManager transactionManager) {
        super(transactionManager, null);

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
}
