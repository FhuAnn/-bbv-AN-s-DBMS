package interfaces;

public interface ExecutionOperator {
    void init();

    Object next();

    void close();
}
package interfaces;

import classes.queryprocessor.Tuple;

public interface ExecutionOperator {
    void init();

    Tuple next();

    void close();
}
