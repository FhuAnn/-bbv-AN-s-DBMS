package classes.iterator;

import classes.metadata.Row;
import interfaces.IExecutionOperator;

public class ProjectionOperator
        extends AbstractExecutionOperator {

    private IExecutionOperator child;

    public ProjectionOperator() {
        // TODO: Implement
    }

    public ProjectionOperator(
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
