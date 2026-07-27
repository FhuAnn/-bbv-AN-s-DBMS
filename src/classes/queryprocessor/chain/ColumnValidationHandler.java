package classes.queryprocessor.chain;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import classes.metadata.ColumnMetadata;
import classes.metadata.Table;
import interfaces.IQueryValidationCatalog;

public class ColumnValidationHandler
        extends AbstractQueryValidationHandler {

    private final IQueryValidationCatalog catalogManager;

    public ColumnValidationHandler(
            IQueryValidationCatalog catalogManager) {

        this.catalogManager = Objects.requireNonNull(
                catalogManager,
                "Catalog manager must not be null");
    }

    @Override
    protected QueryValidationResult doValidate(
            QueryValidationContext context) {

        Table table = catalogManager.getTable(
                context.getSchemaName(),
                context.getTableName());

        if (table == null) {
            return QueryValidationResult.failure(
                    getClass().getSimpleName(),
                    "Cannot validate columns because table does not exist");
        }

        List<String> requestedColumns = context.getReferencedColumns();

        Set<String> existingColumns = table.getColumns()
                .stream()
                .map(ColumnMetadata::getName)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        for (String columnName : requestedColumns) {
            if (columnName == null
                    || !existingColumns.contains(
                            columnName.toLowerCase())) {

                return QueryValidationResult.failure(
                        getClass().getSimpleName(),
                        "Column does not exist: " + columnName);
            }
        }

        return QueryValidationResult.success();
    }
}
