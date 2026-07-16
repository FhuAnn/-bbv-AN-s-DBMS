package classes.queryprocessor;

import classes.authentication.ExpressionNode;
import interfaces.ExecutionOperator;

public class NestedLoopJoinOperator implements ExecutionOperator {
    private final ExecutionOperator outerChild;
    private final ExecutionOperator innerChild;
    private final ExpressionNode joinPredicate;
    private Tuple currentOuter;

    NestedLoopJoinOperator(ExecutionOperator outerChild, ExecutionOperator innerChild, ExpressionNode joinPredicate) {
        this.outerChild = outerChild;
        this.innerChild = innerChild;
        this.joinPredicate = joinPredicate;
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
