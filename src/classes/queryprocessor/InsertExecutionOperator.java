package classes.queryprocessor;

import java.util.List;

import interfaces.ExecutionOperator;

public class InsertExecutionOperator implements ExecutionOperator {
    private final String tableId;
    private final List<Tuple> valuesToInsert;
    private final BufferPoolManager bufferPoolManager;
    private boolean executed;

    public InsertExecutionOperator(String tableId, List<Tuple> valuesToInsert, BufferPoolManager bufferPoolManager) {
        this.tableId = tableId;
        this.valuesToInsert = valuesToInsert;
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
