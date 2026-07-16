package classes.queryprocessor;

import classes.authentication.ExpressionNode;
import interfaces.ASTNode;
import interfaces.ASTVisitor;

public class PolicyRewriterVisitor implements ASTVisitor<ASTNode> {
    private final ExpressionNode policyCondition;

    PolicyRewriterVisitor(ExpressionNode policyCondition) {
        this.policyCondition = policyCondition;
    }

    @Override
    public ASTNode visitSelectStatement(SelectStatementNode node) {
        return null;
    }

    @Override
    public ASTNode visitInsertStatement(InsertStatementNode node) {
        return null;
    }

    @Override
    public ASTNode visitBinaryExpression(BinaryExpressionNode node) {
        return null;
    }

    @Override
    public ASTNode visitIdentifier(IdentifierNode node) {
        return null;
    }

    @Override
    public ASTNode visitLiteral(LiteralNode node) {
        return null;
    }

    @Override
    public ASTNode visitTableReference(TableReferenceNode node) {
        return null;
    }
}
