package classes.tx;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class TransactionManager {
    public enum Status {
        ACTIVE, COMMITTED, ROLLED_BACK
    }

    public static final class Transaction {
        private final UUID id;
        private Status status;

        private Transaction(UUID id) {
            this.id = id;
            this.status = Status.ACTIVE;
        }

        public UUID getId() {
            return id;
        }

        public Status getStatus() {
            return status;
        }
    }

    private final Map<UUID, Transaction> transactions = new LinkedHashMap<>();

    public Transaction begin() {
       return null;
    }

    public void commit(UUID transactionId) {
       
    }

    public void rollback(UUID transactionId) {
       
    }

    public Transaction getTransaction(UUID transactionId) {
       return null;
    }

    public boolean containsTransaction(UUID transactionId) {
        return true;
    }

    public int getTransactionCount() {
        return 0;
    }

    public Map<UUID, Transaction> getTransactions() {
        return null;
    }

    private Transaction requireActive(UUID transactionId) {
       return null;
    }

    private void validateId(UUID transactionId) {
       
    }
}
