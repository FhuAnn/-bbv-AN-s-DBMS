package interfaces;

import classes.queryprocessor.BinaryExpressionNode;
import classes.queryprocessor.IdentifierNode;
import classes.queryprocessor.InsertStatementNode;
import classes.queryprocessor.LiteralNode;
import classes.queryprocessor.SelectStatementNode;
import classes.queryprocessor.TableReferenceNode;

public interface ASTVisitor<T> {
    T visitSelectStatement(SelectStatementNode node);

    T visitInsertStatement(InsertStatementNode node);

    T visitBinaryExpression(BinaryExpressionNode node);

    T visitIdentifier(IdentifierNode node);

    T visitLiteral(LiteralNode node);

    T visitTableReference(TableReferenceNode node);
}
