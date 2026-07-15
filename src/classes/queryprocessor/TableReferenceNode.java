package classes.queryprocessor;

import classes.authentication.ASTNodeType;

public class TableReferenceNode extends AbstractASTNode {
    String tableName;
    String alias;

    TableReferenceNode(String tableName, String alias) {
        this.tableName = tableName;
        this.alias = alias;
    }

    @Override
    public ASTNodeType getType() {
        return ASTNodeType.TABLE_REFERENCE;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitTableReference(this);
    }

    public String getTableName() {
        return tableName;
    }

    public String getAlias() {
        return alias;
    }
}
