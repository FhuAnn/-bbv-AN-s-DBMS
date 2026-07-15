package classes.queryprocessor;

import classes.authentication.ASTNodeType;
import classes.authentication.ExpressionNode;
import interfaces.ASTVisitor;

public class LiteralNode extends AbstractASTNode implements ExpressionNode {
    Object value;
    String rawType;

    LiteralNode(Object value, String rawType) {
        this.value = value;
        this.rawType = rawType;
    }

    @Override
    public ASTNodeType getType() {
        return ASTNodeType.LITERAL;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitLiteral(this);
    }

    public Object getValue() {
        return value;
    }


    public String getRawType() {
        return rawType;
    }
}
