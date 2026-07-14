package classes;

import java.util.List;

public class InsertExecutionOperator implements ExecutionOperator {
    private final String tableId;
    private final List<Tuple> valuesToInsert;
    private final BufferPoolManager bufferPoolManager;
    private boolean executed;

    InsertExecutionOperator(String tableId, List<Tuple> valuesToInsert, BufferPoolManager bufferPoolManager) {
        this.tableId = tableId;
        this.valuesToInsert = valuesToInsert;
        this.bufferPoolManager = bufferPoolManager;
    }

    @Override
    public void init() {
        executed = false;
    }

    @Override
    public Tuple next() {
        if (executed) {
            return null;
        }
        for (Tuple tuple : valuesToInsert) {
            bufferPoolManager.insertRow(tableId, tuple);
        }
        executed = true;
        Tuple affected = new Tuple();
        affected.values.put("rowCount", valuesToInsert.size());
        return affected;
    }

    @Override
    public void close() {
    }
}
