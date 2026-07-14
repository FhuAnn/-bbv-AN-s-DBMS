package classes;

import classes.authentication.ExpressionNode;

public class LogicalFilter extends LogicalOperator {
    final ExpressionNode predicate;

    LogicalFilter(ExpressionNode predicate) {
        this.predicate = predicate;
    }
}
