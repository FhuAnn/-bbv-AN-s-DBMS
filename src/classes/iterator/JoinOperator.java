package classes.iterator;

import classes.metadata.Row;
import interfaces.IExecutionOperator;

public class JoinOperator
        extends AbstractExecutionOperator {

    private IExecutionOperator leftChild;
    private IExecutionOperator rightChild;

    private Row currentLeftRow;

    public JoinOperator() {
        // TODO: Implement
    }

    public JoinOperator(
            IExecutionOperator leftChild,
            IExecutionOperator rightChild) {
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

    public IExecutionOperator getLeftChild() {
        return null;
    }

    public IExecutionOperator getRightChild() {
        return null;
    }

}
