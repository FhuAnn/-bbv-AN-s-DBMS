package core.classes.tx.command;

import java.util.UUID;

import core.classes.tx.TransactionManager;
import core.enums.transaction.TransactionCommandType;

public class RollbackTransactionCommand extends AbstractTransactionCommand {
 public RollbackTransactionCommand(
            TransactionManager transactionManager,
            UUID transactionId
    ) {
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
