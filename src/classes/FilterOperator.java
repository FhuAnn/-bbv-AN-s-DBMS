package classes;

import classes.authentication.ExpressionNode;

public class FilterOperator implements ExecutionOperator {
    private final ExecutionOperator childOperator;
    private final ExpressionNode predicate;

    FilterOperator(ExecutionOperator childOperator, ExpressionNode predicate) {
        this.childOperator = childOperator;
        this.predicate = predicate;
    }

    @Override
    public void init() {
        childOperator.init();
    }

    @Override
    public Tuple next() {
        Tuple tuple;
        while ((tuple = childOperator.next()) != null) {
            if (ExpressionEvaluator.evaluate(tuple, predicate)) {
                return tuple;
            }
        }
        return null;
    }

    @Override
    public void close() {
        childOperator.close();
    }
}
