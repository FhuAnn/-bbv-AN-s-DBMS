package classes.queryprocessor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import interfaces.ExecutionOperator;

public class IndexScanOperator implements ExecutionOperator {
    private final String indexId;
    private final Object searchKey;
    private final BufferPoolManager bufferPoolManager;
    private int currentSlotId;
    private List<Tuple> rows = Collections.emptyList();

    IndexScanOperator(String indexId, Object searchKey, BufferPoolManager bufferPoolManager) {
        this.indexId = indexId;
        this.searchKey = searchKey;
        this.bufferPoolManager = bufferPoolManager;
    }

    @Override
    public void init() {
        currentSlotId = 0;
        rows = bufferPoolManager.getTableRows(indexId);
    }

    @Override
    public Tuple next() {
        while (currentSlotId < rows.size()) {
            Tuple tuple = rows.get(currentSlotId++).copy();
            if (Objects.equals(tuple.values.get("key"), searchKey)) {
                return tuple;
            }
        }
        return null;
    }

    @Override
    public void close() {
    }
}
