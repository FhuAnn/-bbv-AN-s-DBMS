package classes.iterator;

import classes.metadata.Row;
import interfaces.IExecutionOperator;

public class LimitOperator
        extends AbstractExecutionOperator {

    private IExecutionOperator child;
    private long limit;
    private long returnedCount;

    public LimitOperator() {
        // TODO: Implement
    }

    public LimitOperator(
            IExecutionOperator child,
            long limit) {
        // TODO: Implement
    }

    @Override
    public void init() {
        // TODO: Implement
    }

    @Override
    public Row next() {
        return null;
    }

    @Override
    public void close() {
        // TODO: Implement
    }

    public long getLimit() {
        return 0L;
    }

    public long getReturnedCount() {
        return 0L;
    }
}
