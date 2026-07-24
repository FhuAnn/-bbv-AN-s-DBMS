package classes.queryprocessor.node;

import classes.authentication.ASTNodeType;
import interfaces.IASTNode;
import interfaces.ASTVisitor;

public class TableNode implements IASTNode {

    private String tableName;
    private String alias;

    public TableNode() {
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

    public String getTableName() {
        return null;
    }

    public String getAlias() {
        return null;
    }
}
