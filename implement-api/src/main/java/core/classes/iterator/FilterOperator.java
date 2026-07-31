package core.classes.iterator;

import core.classes.metadata.Row;
import core.interfaces.IExecutionOperator;
import core.interfaces.IRowPredicate;

public class FilterOperator extends AbstractExecutionOperator implements IExecutionOperator {
    private final IExecutionOperator child;
    private final IRowPredicate predicate;

    public FilterOperator(IExecutionOperator child, IRowPredicate predicate) {
        this.child = child;
        this.predicate = predicate;
    }

    public FilterOperator() {
        // TODO: Implement
        child = null;
        predicate = null;
    }

    public FilterOperator(
            IExecutionOperator child) {
        // TODO: Implement
        this.child = child;
        predicate = null;
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
