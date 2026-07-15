package classes.queryprocessor;

import classes.authentication.ASTNodeType;
import classes.authentication.ExpressionNode;
import interfaces.ASTVisitor;

public class IdentifierNode extends AbstractASTNode implements ExpressionNode {
    String name;

    IdentifierNode(String name) {
        this.name = name;
    }

    @Override
    public ASTNodeType getType() {
        return ASTNodeType.IDENTIFIER;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitIdentifier(this);
    }
}
