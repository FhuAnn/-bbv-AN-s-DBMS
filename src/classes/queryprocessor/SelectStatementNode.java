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
        return null;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }

    public TableReferenceNode getFromClause() {
        return null;
    }
     public ExpressionNode getWhereClause() {
         return null;
     }

    public List<ASTNode> getProjectionList() {
        return null;
    }

    public int getLimit() {
        return 0;
    }

    public int getOffset() {
        return 0;
    }

    public void setFromClause(TableReferenceNode fromClause) {
    }

    public void setWhereClause(ExpressionNode whereClause) {
    }
}
