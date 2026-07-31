package core.classes.tx.command;

import core.classes.tx.TransactionManager;
import core.enums.transaction.TransactionCommandType;

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
