package classes.queryprocessor;

import classes.metadata.Index;

public class IndexScan implements ScanOperator {
    public Index index;

    public Tuple next() {
        return null;
    }
}