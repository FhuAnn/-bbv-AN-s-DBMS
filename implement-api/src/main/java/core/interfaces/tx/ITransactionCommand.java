package core.interfaces.tx;

import java.util.UUID;

import  core.classes.tx.command.TransactionCommandResult;
import  core.enums.transaction.TransactionCommandType;

public interface ITransactionCommand {
    TransactionCommandResult execute();

    TransactionCommandType getType();

    UUID getTransactionId();
}
