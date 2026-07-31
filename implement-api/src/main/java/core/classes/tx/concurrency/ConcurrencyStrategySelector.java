package core.classes.tx.concurrency;

import java.util.List;

import core.interfaces.tx.IConcurrencyControlStrategy;

public class ConcurrencyStrategySelector {
    private List<IConcurrencyControlStrategy> strategies;

    public ConcurrencyStrategySelector(
            List<IConcurrencyControlStrategy> strategies) {
        // TODO: Implement
    }

    public IConcurrencyControlStrategy select(
            TransactionOptions options,
            WorkloadProfile workload) {
        // TODO: Implement
        return null;
    }

    public void register(
           IConcurrencyControlStrategy strategy) {
        // TODO: Implement
    }

    public List<IConcurrencyControlStrategy> getStrategies() {
        // TODO: Implement
        return List.of();
    }
}
