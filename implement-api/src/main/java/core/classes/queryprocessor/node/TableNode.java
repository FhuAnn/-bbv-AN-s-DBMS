package core.classes.queryprocessor.node;

import core.classes.authentication.ASTNodeType;
import core.interfaces.IASTNode;
import core.interfaces.ASTVisitor;

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
