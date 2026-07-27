package classes.queryprocessor.chain;

import java.util.List;
import java.util.Map;

public final class QueryValidationMetadata {
    private final String schemaName;
    private final String tableName;
    private final List<String> referencedColumns;
    private final Map<String, Object> suppliedValues;
    private final String requiredAction;

    public QueryValidationMetadata(
            String schemaName,
            String tableName,
            List<String> referencedColumns,
            Map<String, Object> suppliedValues,
            String requiredAction) {

        this.schemaName = schemaName;
        this.tableName = tableName;
        this.referencedColumns = referencedColumns == null
                ? List.of()
                : List.copyOf(referencedColumns);

        this.suppliedValues = suppliedValues == null
                ? Map.of()
                : Map.copyOf(suppliedValues);

        this.requiredAction = requiredAction;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public List<String> getReferencedColumns() {
        return referencedColumns;
    }

    public Map<String, Object> getSuppliedValues() {
        return suppliedValues;
    }

    public String getRequiredAction() {
        return requiredAction;
    }
}
