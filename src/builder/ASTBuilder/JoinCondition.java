package builder.ASTBuilder;

import java.beans.Expression;
import java.util.Objects;

public class JoinCondition {
    private Expression expression;

    public JoinCondition(Expression expression) {
        this.expression = Objects.requireNonNull(expression);
    }

    public Expression getExpression() {
        return expression;
    }
}
