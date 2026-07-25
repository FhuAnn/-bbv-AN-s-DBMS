package classes.iterator;

import java.util.List;

import builder.ASTBuilder.ColumnReference;
import classes.metadata.Row;
import interfaces.IExecutionOperator;

public class ProjectionOperator
        extends AbstractExecutionOperator {

    private IExecutionOperator child;
    private final List<ColumnReference> columns;

    public ProjectionOperator() {
        // TODO: Implement
        child = null;
        columns = null;
    }

    public ProjectionOperator(IExecutionOperator child, List<ColumnReference> columns) {
        this.child = child;
        this.columns = List.copyOf(columns);
    }

    public ProjectionOperator(
            IExecutionOperator child) {
        // TODO: Implement
        child = child;
        columns = null;
    }

    @Override
    public void init() {
        // TODO: Implement
    }

    @Override
    public Row next() {
        return null;
    }

    @Override
    public void close() {
        // TODO: Implement
    }

    public IExecutionOperator getChild() {
        return null;
    }

}
