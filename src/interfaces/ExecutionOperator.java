package interfaces;

public interface ExecutionOperator {
    void init();

    Object next();

    void close();
}
