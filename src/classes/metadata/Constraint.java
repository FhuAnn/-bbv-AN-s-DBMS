package classes.metadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
        this.columnNames = new ArrayList<>();
        this.enabled = true;
        this.referencedTableId = null;
        this.referencedColumnNames = new ArrayList<>();
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
        this.columnNames = new ArrayList<>(columnNames);
        this.enabled = true;
        this.referencedTableId = null;
        this.referencedColumnNames = new ArrayList<>();
        this.checkExpression = null;
    }

    public UUID getId() {
        return id;
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
        return Collections.unmodifiableList(columnNames);
    }

    public void setColumnNames(List<String> columnNames) {
        // TODO: Implement
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = true;
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
        return Collections.unmodifiableList(referencedColumnNames);
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
        requireRowAndValues(row, existingKeys, "Existing keys");
        if (!enabled) {
            return true;
        }

        List<Object> key = extractValues(row, columnNames);

        if (key.stream().anyMatch(Objects::isNull)) {
            return false;
        }

        return !existingKeys.contains(key);
    }

    public boolean validateUnique(
            Row row,
            Set<List<Object>> existingValues) {
        requireRowAndValues(row, existingValues, "Existing values");

        if (!enabled) {
            return true;
        }

        List<Object> values = extractValues(row, columnNames);

        if (values.stream().anyMatch(Objects::isNull)) {
            return true;
        }

        return !existingValues.contains(values);
    }

    public boolean validateNotNull(Row row) {
        if (row == null) {
            throw new IllegalArgumentException(
                    "Row must not be null.");
        }

        if (!enabled) {
            return true;
        }

        for (String columnName : columnNames) {
            if (row.getValue(columnName) == null) {
                return false;
            }
        }

        return true;
    }

    public boolean validateForeignKey(
            Row row,
            Set<List<Object>> referencedValues) {
        requireRowAndValues(
                row,
                referencedValues,
                "Referenced values");

        if (!enabled) {
            return true;
        }

        List<Object> values = extractValues(row, columnNames);

        if (values.stream().anyMatch(Objects::isNull)) {
            return true;
        }

        return referencedValues.contains(values);
    }

    public boolean validateCheck(Row row) {
        if (row == null) {
            throw new IllegalArgumentException(
                    "Row must not be null.");
        }

        if (!enabled) {
            return true;
        }

        if (checkPredicate == null) {
            throw new IllegalStateException(
                    "Check predicate has not been configured.");
        }

        return checkPredicate.test(row);
    }

    public boolean isValidDefinition() {
        if (id == null
                || name == null
                || name.trim().isEmpty()
                || type == null
                || columnNames.isEmpty()) {
            return false;
        }

        return switch (type) {
            case PRIMARY_KEY, UNIQUE, NOT_NULL -> true;
            case FOREIGN_KEY ->
                referencedTableId != null
                        && !referencedColumnNames.isEmpty()
                        && referencedColumnNames.size() == columnNames.size();
            case CHECK ->
                checkExpression != null
                        && !checkExpression.trim().isEmpty()
                        && checkPredicate != null;
        };
    }

    private static List<Object> extractValues(
            Row row,
            List<String> columnNames) {
        List<Object> values = new ArrayList<>();

        for (String columnName : columnNames) {
            values.add(row.getValue(columnName));
        }

        return Collections.unmodifiableList(values);
    }

    private static void requireRowAndValues(
            Row row,
            Set<List<Object>> values,
            String valueSetName) {
        if (row == null) {
            throw new IllegalArgumentException(
                    "Row must not be null.");
        }

        if (values == null) {
            throw new IllegalArgumentException(
                    valueSetName + " must not be null.");
        }
    }

    private static void validateName(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Constraint name must not be null or blank.");
        }
    }

    private static void validateColumnNames(
            List<String> columnNames) {
        if (columnNames == null || columnNames.isEmpty()) {
            throw new IllegalArgumentException(
                    "Constraint must contain at least one column.");
        }

        for (String columnName : columnNames) {
            if (columnName == null || columnName.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Column name must not be null or blank.");
            }
        }
    }
}