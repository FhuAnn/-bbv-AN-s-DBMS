package builder;

import java.util.List;
import java.util.UUID;

import classes.metadata.definition.ConstraintDefinition;
import enums.ConstraintType;

public class ConstraintDefinitionBuilder {
    private String name;
    private ConstraintType type;
    private UUID tableId;
    private List<UUID> columnIds;
    private UUID referencedTableId;
    private List<UUID> referencedColumnIds;
    private String expression;

    public ConstraintDefinitionBuilder() {
    }

    public static ConstraintDefinitionBuilder builder() {
        return new ConstraintDefinitionBuilder();
    }

    public ConstraintDefinitionBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ConstraintDefinitionBuilder type(ConstraintType type) {
        this.type = type;
        return this;
    }

    public ConstraintDefinitionBuilder tableId(UUID tableId) {
        this.tableId = tableId;
        return this;
    }

    public ConstraintDefinitionBuilder columnIds(List<UUID> columnIds) {
        this.columnIds = columnIds;
        return this;
    }

    public ConstraintDefinitionBuilder referencedTableId(UUID referencedTableId) {
        this.referencedTableId = referencedTableId;
        return this;
    }

    public ConstraintDefinitionBuilder referencedColumnIds(List<UUID> referencedColumnIds) {
        this.referencedColumnIds = referencedColumnIds;
        return this;
    }

    public ConstraintDefinitionBuilder expression(String expression) {
        this.expression = expression;
        return this;
    }

    public ConstraintDefinition build() {
        validateRequiredFields();
        validateConfiguration();
        ConstraintDefinition result = new ConstraintDefinition(
                name,
                type,
                tableId,
                columnIds,
                referencedTableId,
                referencedColumnIds,
                expression);
        return result;
    }

    private void validateRequiredFields() {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Constraint name must not be blank");
        }
        if (type == null) {
            throw new IllegalArgumentException(
                    "Constraint type must not be null");
        }
        if (tableId == null) {
            throw new IllegalArgumentException(
                    "Table ID must not be null");
        }
    }

    private void validateConfiguration() {
        if (type == ConstraintType.PRIMARY_KEY || type == ConstraintType.UNIQUE) {
            if (columnIds == null || columnIds.isEmpty()) {
                throw new IllegalArgumentException(
                        "Column IDs must not be null or empty for PRIMARY_KEY and UNIQUE constraints");
            }
        } else if (type == ConstraintType.FOREIGN_KEY) {
            if (referencedTableId == null) {
                throw new IllegalArgumentException(
                        "Referenced table ID must not be null for FOREIGN_KEY constraints");
            }
            if (referencedColumnIds == null || referencedColumnIds.isEmpty()) {
                throw new IllegalArgumentException(
                        "Referenced column IDs must not be null or empty for FOREIGN_KEY constraints");
            }
        } else if (type == ConstraintType.CHECK) {
            if (expression == null || expression.isBlank()) {
                throw new IllegalArgumentException(
                        "Expression must not be null or blank for CHECK constraints");
            }
        }
    }
}
