package interfaces;

import classes.queryprocessor.Tuple;

public interface ExecutionOperator {
    void init();

    Tuple next();

    void close();
}
