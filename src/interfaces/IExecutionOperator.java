package interfaces;

public interface IExecutionOperator {
    void init();

    Object next();

    void close();
}
