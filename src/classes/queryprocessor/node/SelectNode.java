package classes.queryprocessor.node;

import java.util.List;

import classes.authentication.ASTNodeType;
import interfaces.IASTNode;
import interfaces.ASTVisitor;

public class SelectNode implements IASTNode {

    private List<ColumnNode> columns;
    private TableNode from;
    private ExpressionNode where;
    private List<JoinNode> joins;

    public SelectNode() {
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

    public List<ColumnNode> getColumns() {
        return List.of();
    }

    public TableNode getFrom() {
        return null;
    }

    public ExpressionNode getWhere() {
        return null;
    }

    public List<JoinNode> getJoins() {
        return List.of();
    }
}
