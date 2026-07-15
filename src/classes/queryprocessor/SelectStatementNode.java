package classes.queryprocessor;

import java.util.ArrayList;
import java.util.List;

import classes.authentication.ASTNodeType;
import classes.authentication.ExpressionNode;
import interfaces.ASTNode;
import interfaces.ASTVisitor;

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

    public TableReferenceNode getFromClause() {
        return fromClause;
    }
     public ExpressionNode getWhereClause() {
        return whereClause;
    }

    public List<ASTNode> getProjectionList() {
        return projectionList;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}
