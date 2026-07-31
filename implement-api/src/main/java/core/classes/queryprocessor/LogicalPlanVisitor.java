package core.classes.queryprocessor;
import core.classes.metadata.Catalog;
import core.classes.queryprocessor.node.ColumnNode;
import core.classes.queryprocessor.node.DeleteNode;
import core.classes.queryprocessor.node.ExpressionNode;
import core.classes.queryprocessor.node.InsertNode;
import core.classes.queryprocessor.node.JoinNode;
import core.classes.queryprocessor.node.SelectNode;
import core.classes.queryprocessor.node.TableNode;
import core.classes.queryprocessor.node.UpdateNode;
import core.interfaces.ASTVisitor;

public class LogicalPlanVisitor
        implements ASTVisitor {

    private Catalog catalog;
    private LogicalPlan logicalPlan;

    public LogicalPlanVisitor() {
        // TODO: Implement
    }

    public LogicalPlanVisitor(Catalog catalog) {
        // TODO: Implement
    }

    @Override
    public Object visitSelect(SelectNode node) {
        return null;
    }

    @Override
    public Object visitInsert(InsertNode node) {
        return null;
    }

    @Override
    public Object visitUpdate(UpdateNode node) {
        return null;
    }

    @Override
    public Object visitDelete(DeleteNode node) {
        return null;
    }

    @Override
    public Object visitTable(TableNode node) {
        return null;
    }

    @Override
    public Object visitColumn(ColumnNode node) {
        return null;
    }

    @Override
    public Object visitJoin(JoinNode node) {
        return null;
    }

    @Override
    public Object visitExpression(ExpressionNode node) {
        return null;
    }

    public LogicalPlan getPlan() {
        return null;
    }
}
