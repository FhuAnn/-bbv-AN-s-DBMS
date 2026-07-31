package core.classes.tx.concurrency;

import core.classes.tx.ConflictDetector;
import core.classes.tx.Resource;
import core.classes.tx.Transaction;
import core.classes.tx.VersionManager;
import core.enums.strategy.ConcurrencyStrategyType;
import core.interfaces.tx.IConcurrencyControlStrategy;

public class OptimisticConcurrencyStrategy implements IConcurrencyControlStrategy {

    private VersionManager versionManager;
    private ConflictDetector conflictDetector;

    public OptimisticConcurrencyStrategy(
            VersionManager versionManager,
            ConflictDetector conflictDetector) {
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
