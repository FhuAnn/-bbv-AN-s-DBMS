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
        outerChild.init();
        innerChild.init();
        currentOuter = outerChild.next();
    }

    @Override
    public Tuple next() {
        while (currentOuter != null) {
            Tuple inner;
            while ((inner = innerChild.next()) != null) {
                Tuple joined = new Tuple();
                joined.values.putAll(currentOuter.values);
                joined.values.putAll(inner.values);
                if (joinPredicate == null || ExpressionEvaluator.evaluate(joined, joinPredicate)) {
                    return joined;
                }
            }
            innerChild.close();
            innerChild.init();
            currentOuter = outerChild.next();
        }
        return null;
    }

    @Override
    public void close() {
        outerChild.close();
        innerChild.close();
    }
}
