package core.classes.tx.command;

import java.util.List;
import java.util.Queue;

import interfaces.tx.ITransactionCommand;

public class TransactionCommandInvoker {
    private Queue<ITransactionCommand> commandQueue;
    private List<TransactionCommandRecord> history;

    public TransactionCommandInvoker() {
        // TODO: Implement
    }

    public TransactionCommandResult execute(
            ITransactionCommand command) {
        // TODO: Implement
        return null;
    }

    public void enqueue(
            ITransactionCommand command) {
        // TODO: Implement
    }

    public TransactionCommandResult executeNext() {
        // TODO: Implement
        return null;
    }

    public List<TransactionCommandResult> executeAll() {
        // TODO: Implement
        return List.of();
    }

    public boolean hasPendingCommands() {
        // TODO: Implement
        return false;
    }

    public int getQueueSize() {
        // TODO: Implement
        return 0;
    }

    public List<TransactionCommandRecord> getHistory() {
        // TODO: Implement
        return List.of();
    }

    private void record(
            ITransactionCommand command,
            TransactionCommandResult result) {
        // TODO: Implement
    }
}
