package core.classes.tx.state;

import java.util.concurrent.locks.Lock;

import core.classes.tx.Transaction;
import core.enums.TransactionStateType;

public class CommitedTransactionState implements ITransactionState {

  
    @Override
    public void commit(Transaction transaction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'commit'");
    }

    @Override
    public void rollback(Transaction transaction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rollback'");
    }

    @Override
    public void execute(Transaction transaction, ITransactionOperation operation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public void addLock(Transaction transaction, Lock lock) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addLock'");
    }

    @Override
    public void createSavepoint(Transaction transaction, String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createSavepoint'");
    }

    @Override
    public TransactionStateType getType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }

}
