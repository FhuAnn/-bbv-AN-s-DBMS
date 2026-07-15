package classes.queryprocessor;


public interface ASTVisitor<T> {
    T visitSelectStatement(SelectStatementNode node);

    T visitInsertStatement(InsertStatementNode node);

    T visitBinaryExpression(BinaryExpressionNode node);

    T visitIdentifier(IdentifierNode node);

    T visitLiteral(LiteralNode node);

    T visitTableReference(TableReferenceNode node);
}
