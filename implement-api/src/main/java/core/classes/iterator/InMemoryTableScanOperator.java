package core.classes.iterator;

import java.util.List;

import core.classes.metadata.Row;
import core.interfaces.IExecutionOperator;


public class InMemoryTableScanOperator implements IExecutionOperator {
    private final List<Row> rows;
    private int index;

    public InMemoryTableScanOperator(List<Row> rows) {
        this.rows = List.copyOf(rows);
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }

    @Override
    public Object next() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'next'");
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'close'");
    }

}
