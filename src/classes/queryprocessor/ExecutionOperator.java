package classes.queryprocessor;

public interface ExecutionOperator {
    void init();

    Tuple next();

    void close();
}
