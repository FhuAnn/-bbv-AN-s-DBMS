package core.classes.queryprocessor.chain;

import java.util.Objects;

import core.classes.metadata.Table;
import core.interfaces.IQueryValidationCatalog;

public class TableValidationHandler
        extends AbstractQueryValidationHandler {

    private final IQueryValidationCatalog catalogManager;

    public TableValidationHandler(
            IQueryValidationCatalog catalogManager) {

        this.catalogManager = Objects.requireNonNull(
                catalogManager,
                "Catalog manager must not be null");
    }

    @Override
    protected QueryValidationResult doValidate(
            QueryValidationContext context) {

        String tableName = context.getTableName();

        if (tableName == null || tableName.isBlank()) {
            return QueryValidationResult.failure(
                    getClass().getSimpleName(),
                    "Table name must not be null or blank");
        }

        Table table = catalogManager.getTable(
                context.getSchemaName(),
                tableName);

        if (table == null) {
            return QueryValidationResult.failure(
                    getClass().getSimpleName(),
                    "Table does not exist: "
                            + context.getSchemaName()
                            + "."
                            + tableName);
        }

        return QueryValidationResult.success();
    }
}
