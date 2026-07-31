package core.classes.tx.state;

import java.util.concurrent.locks.Lock;

import classes.tx.Transaction;
import enums.TransactionStateType;

public interface ITransactionState {
    public void commit(Transaction transaction);
    public void rollback(Transaction transaction);
    public void execute(Transaction transaction, ITransactionOperation operation);
    public void addLock(Transaction transaction, Lock lock);
    public void createSavepoint(Transaction transaction, String name);
    public TransactionStateType getType();
}
