package classes.tx.command;

import java.util.UUID;

import classes.tx.TransactionManager;
import enums.transaction.TransactionCommandType;

public class CommitTransactionCommand extends AbstractTransactionCommand {
    public CommitTransactionCommand(
            TransactionManager transactionManager,
            UUID transactionId) {
        super(transactionManager, transactionId);

        // TODO: Implement
    }

    @Override
    public TransactionCommandResult execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public TransactionCommandType getType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }

}
