package core.classes.tx.concurrency;

import classes.tx.LockManager;
import classes.tx.Resource;
import classes.tx.Transaction;
import enums.strategy.ConcurrencyStrategyType;
import interfaces.tx.IConcurrencyControlStrategy;

public class PessimisticConcurrencyStrategy implements IConcurrencyControlStrategy {
    private LockManager lockManager;

    public PessimisticConcurrencyStrategy(
            LockManager lockManager) {
        // TODO: Implement
    }

    @Override
    public void onBegin(
            Transaction transaction,
            ConcurrencyContext context) {
        // TODO: Implement
    }

    @Override
    public Object read(
            Transaction transaction,
            Resource resource,
            ConcurrencyContext context) {
        // TODO: Implement
        return null;
    }

    @Override
    public void write(
            Transaction transaction,
            Resource resource,
            Object value,
            ConcurrencyContext context) {
        // TODO: Implement
    }

    @Override
    public boolean validate(
            Transaction transaction,
            ConcurrencyContext context) {
        // TODO: Implement
        return false;
    }

    @Override
    public void commit(
            Transaction transaction,
            ConcurrencyContext context) {
        // TODO: Implement
    }

    @Override
    public void rollback(
            Transaction transaction,
            ConcurrencyContext context) {
        // TODO: Implement
    }

    @Override
    public boolean supports(
            TransactionOptions options) {
        // TODO: Implement
        return false;
    }

    @Override
    public double estimateCost(
            TransactionOptions options,
            WorkloadProfile workload) {
        // TODO: Implement
        return 0;
    }

    @Override
    public ConcurrencyStrategyType getType() {
        // TODO: Implement
        return null;
    }
}
