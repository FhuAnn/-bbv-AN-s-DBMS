package interfaces;

import classes.queryprocessor.node.ColumnNode;
import classes.queryprocessor.node.DeleteNode;
import classes.queryprocessor.node.ExpressionNode;
import classes.queryprocessor.node.InsertNode;
import classes.queryprocessor.node.JoinNode;
import classes.queryprocessor.node.SelectNode;
import classes.queryprocessor.node.TableNode;
import classes.queryprocessor.node.UpdateNode;

public interface ASTVisitor<T> {
    Object visitSelect(SelectNode node);

    Object visitInsert(InsertNode node);

    Object visitUpdate(UpdateNode node);

    Object visitDelete(DeleteNode node);

    Object visitTable(TableNode node);

    Object visitColumn(ColumnNode node);

    Object visitJoin(JoinNode node);

    Object visitExpression(ExpressionNode node);
}
