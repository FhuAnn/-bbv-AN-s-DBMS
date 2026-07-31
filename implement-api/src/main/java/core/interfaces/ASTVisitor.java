package core.interfaces;

import core.classes.queryprocessor.node.ColumnNode;
import core.classes.queryprocessor.node.DeleteNode;
import core.classes.queryprocessor.node.ExpressionNode;
import core.classes.queryprocessor.node.InsertNode;
import core.classes.queryprocessor.node.JoinNode;
import core.classes.queryprocessor.node.SelectNode;
import core.classes.queryprocessor.node.TableNode;
import core.classes.queryprocessor.node.UpdateNode;

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
