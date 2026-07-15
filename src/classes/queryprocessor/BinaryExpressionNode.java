package classes.queryprocessor;
import classes.authentication.ASTNodeType;
import classes.authentication.ExpressionNode;

public class BinaryExpressionNode extends AbstractASTNode implements ExpressionNode {
    ExpressionNode left;
    String operator;
    ExpressionNode right;

    BinaryExpressionNode() {
    }

    BinaryExpressionNode(ExpressionNode left, String operator, ExpressionNode right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public ASTNodeType getType() {
        return ASTNodeType.BINARY;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitBinaryExpression(this);
    }
}
