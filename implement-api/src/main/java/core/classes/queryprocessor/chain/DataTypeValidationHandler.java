package core.classes.queryprocessor.chain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import core.classes.metadata.ColumnMetadata;
import core.classes.metadata.Table;
import core.enums.DataType;
import core.interfaces.IQueryValidationCatalog;

public class DataTypeValidationHandler
        extends AbstractQueryValidationHandler {

    private final IQueryValidationCatalog catalogManager;

    public DataTypeValidationHandler(
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
                    "Cannot validate data types because table does not exist");
        }

        for (Map.Entry<String, Object> entry : context.getSuppliedValues().entrySet()) {

            ColumnMetadata column = findColumn(
                    table,
                    entry.getKey());

            if (column == null) {
                return QueryValidationResult.failure(
                        getClass().getSimpleName(),
                        "Column does not exist: " + entry.getKey());
            }

            Object value = entry.getValue();

            if (value == null) {
                if (!column.isNullable()) {
                    return QueryValidationResult.failure(
                            getClass().getSimpleName(),
                            "Column does not allow null: "
                                    + column.getName());
                }

                continue;
            }

            if (!matches(column.getDataType(), value)) {
                return QueryValidationResult.failure(
                        getClass().getSimpleName(),
                        "Invalid value type for column "
                                + column.getName()
                                + ". Expected "
                                + column.getDataType()
                                + " but received "
                                + value.getClass().getSimpleName());
            }
        }

        return QueryValidationResult.success();
    }

    private ColumnMetadata findColumn(
            Table table,
            String columnName) {

        return table.getColumns()
                .stream()
                .filter(column -> column.getName().equalsIgnoreCase(columnName))
                .findFirst()
                .orElse(null);
    }

    private boolean matches(
            DataType dataType,
            Object value) {

        return switch (dataType) {
            case INTEGER -> value instanceof Integer;
            case VARCHAR -> value instanceof String;
            case BOOLEAN -> value instanceof Boolean;
            case DECIMAL -> value instanceof BigDecimal
                    || value instanceof Double
                    || value instanceof Float;
            case DATE -> value instanceof LocalDate;
            case TIMESTAMP -> value instanceof LocalDateTime;
            default -> false;
        };
    }
}
