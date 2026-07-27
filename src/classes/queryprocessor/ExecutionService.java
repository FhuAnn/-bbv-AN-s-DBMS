package classes.queryprocessor;

import java.util.Objects;

import interfaces.IQueryValidation;

public class ExecutionService {
    private final ParserService parserService;
    private final IQueryValidation queryValidation;
    private final QueryOptimizer queryOptimizer;
    private final Executor executor;

    public ExecutionService(ParserService parserService,
            IQueryValidation queryValidation,
            QueryOptimizer queryOptimizer,
            Executor executor) {
        this.parserService = Objects.requireNonNull(parserService);
        this.queryValidation = Objects.requireNonNull(queryValidation);
        this.queryOptimizer = Objects.requireNonNull(queryOptimizer);
        this.executor = Objects.requireNonNull(executor);
    }

    public ResultOutput execute(String sql, String authToken) {
        return null;
    }

    public ParserService getParser() {
        return null;
    }

    public CatalogManager getCatalogManager() {
        return null;
    }

    public QueryOptimizer getQueryOptimizer() {
        return null;
    }

    public ResultFormatter getResultFormatter() {
        return null;
    }
}
