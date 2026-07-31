package core.classes.queryprocessor;

import java.util.List;

import core.classes.authentication.UserSession;
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


public class QueryValidationVisitor
        implements ASTVisitor {

    private Catalog catalog;
    private UserSession session;
    //private List<ValidationError> errors;

    public QueryValidationVisitor() {
        // TODO: Implement
    }

    public QueryValidationVisitor(
            Catalog catalog,
            UserSession session) {
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

    // public ValidationResult getResult() {
    //     return null;
    // }

    // public List<ValidationError> getErrors() {
    //     return List.of();
    // }

    public boolean isValid() {
        return false;
    }
}
