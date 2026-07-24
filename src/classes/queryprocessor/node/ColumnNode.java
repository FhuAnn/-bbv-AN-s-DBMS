package classes.queryprocessor.node;

import classes.authentication.ASTNodeType;
import interfaces.IASTNode;
import interfaces.ASTVisitor;

public class ColumnNode implements IASTNode {

    private String tableAlias;
    private String columnName;

    public ColumnNode() {
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

    public String getTableAlias() {
        return null;
    }

    public String getColumnName() {
        return null;
    }
}
