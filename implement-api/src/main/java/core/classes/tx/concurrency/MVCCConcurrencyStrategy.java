package core.classes.tx.concurrency;

import classes.tx.MVCCManager;
import classes.tx.Resource;
import classes.tx.Transaction;
import classes.tx.VersionManager;
import enums.strategy.ConcurrencyStrategyType;
import interfaces.tx.IConcurrencyControlStrategy;

public class MVCCConcurrencyStrategy
        implements IConcurrencyControlStrategy {

    private MVCCManager mvccManager;
    private VersionManager versionManager;

    public MVCCConcurrencyStrategy(
            MVCCManager mvccManager,
            VersionManager versionManager) {
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
