package classes.metadata;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

public class Constraint {

    public enum ConstraintType {
        PRIMARY_KEY,
        UNIQUE,
        NOT_NULL,
        FOREIGN_KEY,
        CHECK
    }

    private UUID id;
    private String name;
    private ConstraintType type;
    private List<String> columnNames;
    private boolean enabled;

    private UUID referencedTableId;
    private List<String> referencedColumnNames;
    private String checkExpression;
    private Predicate<Row> checkPredicate;

    public Constraint() {
        this.id = UUID.randomUUID();
        this.name = "";
        this.type = null;
        this.columnNames = Collections.emptyList();
        this.enabled = true;
        this.referencedTableId = null;
        this.referencedColumnNames = Collections.emptyList();
        this.checkExpression = null;
    }

    public Constraint(
            String name,
            ConstraintType type,
            List<String> columnNames) {

        validateName(name);

        if (type == null) {
            throw new IllegalArgumentException(
                    "Constraint type must not be null.");
        }

        validateColumnNames(columnNames);
        this.id = UUID.randomUUID();
        this.name = name;
        this.type = type;
        this.columnNames = columnNames;
        this.enabled = true;
        this.referencedTableId = null;
        this.referencedColumnNames = Collections.emptyList();
        this.checkExpression = null;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        // TODO: Implement
    }

    public String getName() {
        return name;
    }

    public void rename(String newName) {
        validateName(newName);
        this.name = newName;
    }

    public ConstraintType getType() {
        return type;
    }

    public void setType(ConstraintType type) {
        // TODO: Implement
    }

    public List<String> getColumnNames() {
        return Collections.emptyList();
    }

    public void setColumnNames(List<String> columnNames) {
        // TODO: Implement
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void enable() {
        // TODO: Implement
    }

    public void disable() {
        // TODO: Implement
    }

    public UUID getReferencedTableId() {
        return referencedTableId;
    }

    public void setReferencedTableId(UUID referencedTableId) {
        if (referencedTableId == null) {
            throw new IllegalArgumentException(
                    "Referenced table ID must not be null.");
        }

        this.referencedTableId = referencedTableId;
    }

    public List<String> getReferencedColumnNames() {
        return Collections.emptyList();
    }

    public void setReferencedColumnNames(List<String> referencedColumnNames) {
        validateColumnNames(referencedColumnNames);

        this.referencedColumnNames.clear();
        this.referencedColumnNames.addAll(referencedColumnNames);
    }

    public String getCheckExpression() {
        return checkExpression;
    }

    public void setCheckExpression(String checkExpression) {
        if (checkExpression == null || checkExpression.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Check expression must not be null or blank.");
        }

        this.checkExpression = checkExpression;

    }

    public void setCheckPredicate(Predicate<Row> checkPredicate) {
        if (checkPredicate == null) {
            throw new IllegalArgumentException(
                    "Check predicate must not be null.");
        }

        this.checkPredicate = checkPredicate;
    }

    public boolean validate(Row row) {
        if (row == null) {
            throw new IllegalArgumentException(
                    "Row must not be null.");
        }

        if (!enabled) {
            return true;
        }

        return switch (type) {
            case NOT_NULL -> validateNotNull(row);
            case CHECK -> validateCheck(row);
            case PRIMARY_KEY, UNIQUE, FOREIGN_KEY ->
                throw new IllegalStateException(
                        "Existing or referenced values are required "
                                + "for constraint type: " + type);
        };
    }

    public boolean validatePrimaryKey(
            Row row,
            Set<List<Object>> existingKeys) {
        return false;
    }

    public boolean validateUnique(
            Row row,
            Set<List<Object>> existingValues) {
        return false;
    }

    public boolean validateNotNull(Row row) {
        return false;
    }

    public boolean validateForeignKey(
            Row row,
            Set<List<Object>> referencedValues) {
        return false;
    }

    public boolean validateCheck(Row row) {
        return false;
    }

    public boolean isValidDefinition() {
        return false;
    }
}