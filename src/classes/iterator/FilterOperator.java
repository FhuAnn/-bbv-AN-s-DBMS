package classes.iterator;

import classes.metadata.Row;
import interfaces.IExecutionOperator;

public class FilterOperator extends AbstractExecutionOperator {
    private IExecutionOperator child;

    public FilterOperator() {
        // TODO: Implement
    }

    public FilterOperator(
            IExecutionOperator child) {
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

    public IExecutionOperator getChild() {
        return null;
    }

}
