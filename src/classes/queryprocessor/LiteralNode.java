package classes.queryprocessor;

import classes.authentication.ASTNodeType;
import classes.authentication.ExpressionNode;
import interfaces.ASTVisitor;

public class LiteralNode extends AbstractASTNode implements ExpressionNode {
    Object value;
    String rawType;

    public LiteralNode(Object value, String rawType) {
        this.value = value;
        this.rawType = rawType;
    }

    @Override
    public ASTNodeType getType() {
        return null;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }

    public Object getValue() {
        return null;
    }


    public String getRawType() {
        return null;
    }
}
