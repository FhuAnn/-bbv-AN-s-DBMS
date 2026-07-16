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
        return null;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }

    public TableReferenceNode getTargetTable() {
        return null;
    }

    public List<LiteralNode> getValues() {
        return null;
    }

    public void setTargetTable(TableReferenceNode targetTable) {
    }
    public void setValues(List<LiteralNode> values) {
    }
}
