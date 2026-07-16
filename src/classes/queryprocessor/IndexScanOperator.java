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
    }

    @Override
    public Tuple next() {
        return null;
    }

    @Override
    public void close() {
    }
}
