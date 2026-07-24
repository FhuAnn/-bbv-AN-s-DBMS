package classes.iterator;

import classes.metadata.Row;
import interfaces.IExecutionOperator;

public abstract class AbstractExecutionOperator implements IExecutionOperator {
    private boolean initialized;
    private boolean closed;

    protected AbstractExecutionOperator() {
        this.initialized = false;
        this.closed = false;
    }

    @Override
    public void init() {
        // TODO: Implement
    }

    @Override
    public abstract Row next();

    @Override
    public void close() {
        // TODO: Implement
    }

    public boolean isInitialized() {
        return false;
    }

    public boolean isClosed() {
        return false;
    }

}
