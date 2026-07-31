package core.classes.iterator;

import core.classes.metadata.Row;
import core.classes.metadata.Table;
import core.classes.storageengine.Page;
import core.classes.storageengine.StorageEngine;

public class TableScanOperator extends AbstractExecutionOperator {
    private StorageEngine storageEngine;
    private Table table;

    private Page currentPage;
    private int currentPageId;
    private int currentSlot;

    public TableScanOperator() {
        // TODO: Implement
    }

    public TableScanOperator(
            StorageEngine storageEngine,
            Table table) {
        // TODO: Implement
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

    public Table getTable() {
        return null;
    }

    public int getCurrentPageId() {
        return 0;
    }

    public int getCurrentSlot() {
        return 0;
    }
}
