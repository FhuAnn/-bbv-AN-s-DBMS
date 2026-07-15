package classes.queryprocessor;

import classes.authentication.ExpressionNode;

public class PhysicalFilter extends PhysicalOperatorNode {
    final ExpressionNode predicate;

    PhysicalFilter(ExpressionNode predicate) {
        this.predicate = predicate;
    }

    public ExpressionNode getPredicate() {
        return predicate;
    }
    
}
