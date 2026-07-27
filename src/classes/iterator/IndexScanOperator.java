package classes.iterator;

import classes.metadata.Index;
import classes.metadata.Row;
import classes.metadata.Table;
import classes.queryprocessor.node.Expression;

public class IndexScanOperator
        extends AbstractExecutionOperator {

    private Table table;
    private Index index;
    private Expression predicate;

    public IndexScanOperator(
            Table table,
            Index index,
            Expression predicate
    ) {
        // TODO: Implement

        this.table = null;
        this.index = null;
        this.predicate = null;
    }

    @Override
    public void init() {
        // TODO: Implement
    }

    @Override
    public Row next() {
        // TODO: Implement
        return null;
    }

    @Override
    public void close() {
        // TODO: Implement
    }

    public Table getTable() {
        // TODO: Implement
        return null;
    }

    public Index getIndex() {
        // TODO: Implement
        return null;
    }

    public Expression getPredicate() {
        // TODO: Implement
        return null;
    }
}
