package classes.queryprocessor;

import java.util.Objects;

import classes.authentication.ExpressionNode;

public class ExpressionEvaluator {
    static boolean evaluate(Tuple tuple, ExpressionNode node) {
        if (node == null) {
            return true;
        }
        if (node instanceof BinaryExpressionNode binary) {
            if ("AND".equalsIgnoreCase(binary.operator)) {
                return evaluate(tuple, binary.left) && evaluate(tuple, binary.right);
            }
            Object left = valueOf(tuple, binary.left);
            Object right = valueOf(tuple, binary.right);
            if ("=".equals(binary.operator)) {
                return Objects.equals(left, right);
            }
        }
        return true;
    }

    private static Object valueOf(Tuple tuple, ExpressionNode node) {
        if (node instanceof IdentifierNode identifier) {
            return tuple.values.get(identifier.name);
        }
        if (node instanceof LiteralNode literal) {
            return literal.value;
        }
        return null;
    }
}
