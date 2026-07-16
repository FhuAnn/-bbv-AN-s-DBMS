package classes.queryprocessor;

import classes.authentication.ExpressionNode;
import interfaces.ExecutionOperator;

public class FilterOperator implements ExecutionOperator {
    private final ExecutionOperator childOperator;
    private final ExpressionNode predicate;

    public FilterOperator(ExecutionOperator childOperator, ExpressionNode predicate) {
        this.childOperator = childOperator;
        this.predicate = predicate;
    }

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
