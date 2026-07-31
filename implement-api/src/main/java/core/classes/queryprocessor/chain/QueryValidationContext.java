package core.classes.queryprocessor.chain;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import core.classes.queryprocessor.ASTBuildResult;

public final class QueryValidationContext {

    private final ASTBuildResult astBuildResult;
    private final String userId;
    private final String schemaName;
    private final String tableName;
    private final List<String> referencedColumns;
    private final Map<String, Object> suppliedValues;
    private final String requiredAction;

    public QueryValidationContext(
            ASTBuildResult astBuildResult,
            String userId,
            String schemaName,
            String tableName,
            List<String> referencedColumns,
            Map<String, Object> suppliedValues,
            String requiredAction) {

        this.astBuildResult = Objects.requireNonNull(
                astBuildResult,
                "AST build result must not be null");

        this.userId = Objects.requireNonNull(
                userId,
                "User ID must not be null");

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

    public ASTBuildResult getAstBuildResult() {
        return astBuildResult;
    }

    public String getUserId() {
        return userId;
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

    public String getResourceName() {
        if (schemaName == null || tableName == null) {
            return null;
        }

        return schemaName + "." + tableName;
    }
}
