package core.classes.queryprocessor.chain;

import java.util.Objects;

import core.interfaces.IQueryValidationCatalog;

public class SchemaValidationHandler
        extends AbstractQueryValidationHandler {

    private final IQueryValidationCatalog catalogManager;

    public SchemaValidationHandler(
            IQueryValidationCatalog catalogManager) {

        this.catalogManager = Objects.requireNonNull(
                catalogManager,
                "Catalog manager must not be null");
    }

    @Override
    protected QueryValidationResult doValidate(
            QueryValidationContext context) {

        String schemaName = context.getSchemaName();

        if (schemaName == null || schemaName.isBlank()) {
            return QueryValidationResult.failure(
                    getClass().getSimpleName(),
                    "Schema name must not be null or blank");
        }

        if (!catalogManager.schemaExists(schemaName)) {
            return QueryValidationResult.failure(
                    getClass().getSimpleName(),
                    "Schema does not exist: " + schemaName);
        }

        return QueryValidationResult.success();
    }
}
