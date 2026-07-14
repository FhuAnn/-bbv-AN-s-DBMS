package classes;

import java.util.ArrayList;
import java.util.List;

import classes.authentication.ASTNodeType;
import classes.authentication.ExpressionNode;

public class SelectStatementNode extends AbstractASTNode {
    TableReferenceNode fromClause;
    ExpressionNode whereClause;
    List<ASTNode> projectionList = new ArrayList<>();
    int limit = -1;
    int offset = 0;

    @Override
    public ASTNodeType getType() {
        return ASTNodeType.SELECT;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitSelectStatement(this);
    }
}
