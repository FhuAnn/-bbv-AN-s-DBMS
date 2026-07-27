package classes.queryprocessor.strategy;

import java.util.List;

import classes.metadata.Index;
import classes.metadata.Table;
import classes.metadata.TableStats;
import classes.queryprocessor.node.Expression;

public class ScanContext {
    private Table table;
    private Expression predicate;
    private TableStats statistics;
    private List<Index> indexes;

    public ScanContext() {
        // TODO: Implement
    }

    public ScanContext(
            Table table,
            Expression predicate,
            TableStats statistics,
            List<Index> indexes) {
        // TODO: Implement
    }

    public Table getTable() {
        // TODO: Implement
        return null;
    }

    public Expression getPredicate() {
        // TODO: Implement
        return null;
    }

    public TableStats getStatistics() {
        // TODO: Implement
        return null;
    }

    public List<Index> getIndexes() {
        // TODO: Implement
        return List.of();
    }

    public boolean hasPredicate() {
        // TODO: Implement
        return false;
    }

    public boolean hasIndexes() {
        // TODO: Implement
        return false;
    }
}
