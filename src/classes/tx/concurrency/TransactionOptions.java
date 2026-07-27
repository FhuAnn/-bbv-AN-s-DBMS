package classes.tx.concurrency;

import enums.IsolationLevel;
import enums.strategy.ConcurrencyStrategyType;

public class TransactionOptions {
    private IsolationLevel isolationLevel;
    private boolean readOnly;
    private ConcurrencyStrategyType preferredStrategy;

    public TransactionOptions() {
        // TODO: Implement
    }

    public TransactionOptions(
            IsolationLevel isolationLevel,
            boolean readOnly,
            ConcurrencyStrategyType preferredStrategy) {
        // TODO: Implement
    }

    public IsolationLevel getIsolationLevel() {
        // TODO: Implement
        return null;
    }

    public boolean isReadOnly() {
        // TODO: Implement
        return false;
    }

    public ConcurrencyStrategyType getPreferredStrategy() {
        // TODO: Implement
        return null;
    }
}
