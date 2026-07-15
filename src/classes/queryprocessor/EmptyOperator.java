package classes.queryprocessor;

import interfaces.ExecutionOperator;

public class EmptyOperator implements ExecutionOperator {
    @Override
    public void init() {
    }

    @Override
    public Tuple next() {
        return null;
    }

    @Override
    public void close() {
    }
}
