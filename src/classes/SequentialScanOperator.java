package classes;

import java.util.List;

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
        currentSlotId = 0;
        bufferPoolManager.pinPage(tableId + "_page_0");
    }

    @Override
    public Tuple next() {
        List<Tuple> rows = bufferPoolManager.getTableRows(tableId);
        if (currentSlotId >= rows.size()) {
            return null;
        }
        return rows.get(currentSlotId++).copy();
    }

    @Override
    public void close() {
        bufferPoolManager.unpinPage(tableId + "_page_0", false);
    }
}
