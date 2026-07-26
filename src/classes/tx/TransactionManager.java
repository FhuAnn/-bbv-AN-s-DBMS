package classes.tx;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class TransactionManager {

    private Map<UUID, Transaction> transactions = new LinkedHashMap<>();

    public TransactionManager() {
        // TODO: Implement

        this.transactions = null;
    }

    public Transaction begin() {
        // TODO: Implement
        return null;
    }

    public void commit(
            Transaction transaction) {
        // TODO: Implement
    }

    public void rollback(
            Transaction transaction) {
        // TODO: Implement
    }

    public Optional<Transaction> findById(
            UUID transactionId) {
        // TODO: Implement
        return Optional.empty();
    }

    public List<Transaction> getActiveTransactions() {
        // TODO: Implement
        return List.of();
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
