package interfaces.tx;

import java.util.UUID;

import classes.tx.command.TransactionCommandResult;
import enums.transaction.TransactionCommandType;

public interface ITransactionCommand {
    TransactionCommandResult execute();

    TransactionCommandType getType();

    UUID getTransactionId();
}
