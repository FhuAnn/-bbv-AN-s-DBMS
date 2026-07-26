package classes.tx;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import classes.tx.state.ITransactionOperation;
import enums.TransactionState;
import enums.TransactionStateType;

public class Transaction {

    private UUID id;
    private TransactionState state;

    private Instant startedAt;
    private Instant completedAt;

    private List<Lock> locks;
    private List<ITransactionOperation> operations;
    private List<String> savepoints;

    public Transaction() {
        // TODO: Implement

        this.id = null;
        this.state = null;
        this.startedAt = null;
        this.completedAt = null;
        this.locks = null;
        this.operations = null;
        this.savepoints = null;
    }

    public void commit() {
        // TODO: Implement
    }

    public void rollback() {
        // TODO: Implement
    }

    public void execute(
            ITransactionOperation operation) {
        // TODO: Implement
    }

    public void addLock(
            Lock lock) {
        // TODO: Implement
    }

    public void createSavepoint(
            String name) {
        // TODO: Implement
    }

    public void changeState(
            TransactionState newState) {
        // TODO: Implement
    }

    public void doCommit() {
        // TODO: Implement
    }

    public void doRollback() {
        // TODO: Implement
    }

    public void doExecute(
            ITransactionOperation operation) {
        // TODO: Implement
    }

    public void doAddLock(
            Lock lock) {
        // TODO: Implement
    }

    public void doCreateSavepoint(
            String name) {
        // TODO: Implement
    }

    public UUID getId() {
        // TODO: Implement
        return null;
    }

    public TransactionState getState() {
        // TODO: Implement
        return null;
    }

    public TransactionStateType getStateType() {
        // TODO: Implement
        return null;
    }

    public Instant getStartedAt() {
        // TODO: Implement
        return null;
    }

    public Instant getCompletedAt() {
        // TODO: Implement
        return null;
    }

    public List<Lock> getLocks() {
        // TODO: Implement
        return List.of();
    }

    public List<ITransactionOperation> getOperations() {
        // TODO: Implement
        return List.of();
    }

    public List<String> getSavepoints() {
        // TODO: Implement
        return List.of();
    }
}
