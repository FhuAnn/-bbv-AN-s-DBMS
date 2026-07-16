package classes.queryprocessor;

import classes.authentication.ASTNodeType;
import interfaces.ASTVisitor;

public class TableReferenceNode extends AbstractASTNode {
    String tableName;
    String alias;

    public TableReferenceNode(String tableName, String alias) {
        this.tableName = tableName;
        this.alias = alias;
    }

    @Override
    public ASTNodeType getType() {
        return null;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }

    public String getTableName() {
        return null;
    }

    public String getAlias() {
        return null;
    }
}
