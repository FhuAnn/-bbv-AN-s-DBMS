package classes;

import classes.authentication.ExpressionNode;

public class PolicyRewriterVisitor implements ASTVisitor<ASTNode> {
    private final ExpressionNode policyCondition;

    PolicyRewriterVisitor(ExpressionNode policyCondition) {
        this.policyCondition = policyCondition;
    }

    @Override
    public ASTNode visitSelectStatement(SelectStatementNode node) {
        if (node.whereClause == null) {
            node.whereClause = policyCondition;
        } else {
            node.whereClause = new BinaryExpressionNode(node.whereClause, "AND", policyCondition);
        }
        return node;
    }

    @Override
    public ASTNode visitInsertStatement(InsertStatementNode node) {
        return node;
    }

    @Override
    public ASTNode visitBinaryExpression(BinaryExpressionNode node) {
        return node;
    }

    @Override
    public ASTNode visitIdentifier(IdentifierNode node) {
        return node;
    }

    @Override
    public ASTNode visitLiteral(LiteralNode node) {
        return node;
    }

    @Override
    public ASTNode visitTableReference(TableReferenceNode node) {
        return node;
    }
}
