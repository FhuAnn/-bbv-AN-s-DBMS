package core.classes.queryprocessor.node;

import core.classes.authentication.ASTNodeType;
import core.interfaces.IASTNode;
import core.interfaces.ASTVisitor;

public class ExpressionNode implements IASTNode {

    private Object value;
    private String operator;
    private ExpressionNode left;
    private ExpressionNode right;

    public ExpressionNode() {
        // TODO: Implement
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return null;
    }

    @Override
    public ASTNodeType getType() {
        return null;
    }

    public Object getValue() {
        return null;
    }

    public String getOperator() {
        return null;
    }

    public ExpressionNode getLeft() {
        return null;
    }

    public ExpressionNode getRight() {
        return null;
    }
}
