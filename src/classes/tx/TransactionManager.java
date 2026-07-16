package classes.tx;


public class TransactionManager {
    private LockManager lockManager = new LockManager();

    public Transaction begin() {
        return null;
    }

    public void commit(Transaction tx) {
    }
    public void rollback(Transaction tx) {
    }
}
