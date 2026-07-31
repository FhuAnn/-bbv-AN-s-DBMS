package core.classes.queryprocessor.node;

import core.classes.authentication.ASTNodeType;
import core.enums.JoinType;
import core.interfaces.IASTNode;
import core.interfaces.ASTVisitor;

public class OrderItem implements IASTNode {

    private JoinType joinType;
    private TableNode table;
    private ExpressionNode condition;

    public OrderItem() {
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
