package classes.queryprocessor;

import java.util.ArrayList;
import java.util.List;

import classes.authentication.ASTNodeType;
import interfaces.ASTVisitor;

public class InsertStatementNode extends AbstractASTNode {
    TableReferenceNode targetTable;
    List<LiteralNode> values = new ArrayList<>();

    @Override
    public ASTNodeType getType() {
        return ASTNodeType.INSERT;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitInsertStatement(this);
    }

    public TableReferenceNode getTargetTable() {
        return targetTable;
    }

    public List<LiteralNode> getValues() {
        return values;
    }

    public void setTargetTable(TableReferenceNode targetTable) {
        this.targetTable = targetTable;
    }
    public void setValues(List<LiteralNode> values) {
        this.values = values;
    }
}
