package classes;

public interface ExecutionOperator {
    void init();

    Tuple next();

    void close();
}
