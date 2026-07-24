package classes.queryprocessor.node;

import classes.authentication.ASTNodeType;
import enums.JoinType;
import interfaces.IASTNode;
import interfaces.ASTVisitor;

public class JoinNode implements IASTNode {

    private JoinType joinType;
    private TableNode table;
    private ExpressionNode condition;

    public JoinNode() {
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

    public JoinType getJoinType() {
        return null;
    }

    public TableNode getTable() {
        return null;
    }

    public ExpressionNode getCondition() {
        return null;
    }
}
