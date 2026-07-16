package classes.queryprocessor;

import java.util.List;

import interfaces.ExecutionOperator;

public class SequentialScanOperator implements ExecutionOperator {
    private final String tableId;
    private final BufferPoolManager bufferPoolManager;
    private int currentSlotId;

    SequentialScanOperator(String tableId, BufferPoolManager bufferPoolManager) {
        this.tableId = tableId;
        this.bufferPoolManager = bufferPoolManager;
    }

    @Override
    public void init() {
    }

    @Override
    public Tuple next() {
        return null;
    }

    @Override
    public void close() {
    }
}
