package classes;

import java.util.ArrayList;
import java.util.List;

import classes.authentication.ASTNodeType;

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
}
