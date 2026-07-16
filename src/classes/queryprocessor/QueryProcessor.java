package classes.queryprocessor;

public class QueryProcessor {
    public Parser parser = new Parser();
    public Optimizer optimizer = new Optimizer();
    public Executor executor = new Executor();
    public QueryValidator validator = new QueryValidator();

    public QueryResult process(String sql) {
        return null;
    }
}