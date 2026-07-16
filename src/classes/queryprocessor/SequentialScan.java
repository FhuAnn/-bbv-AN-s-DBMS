package classes.queryprocessor;

import classes.metadata.TableMetadata;

public class SequentialScan implements ScanOperator {
    public TableMetadata table;

    public Tuple next() {
        return null;
    }
}