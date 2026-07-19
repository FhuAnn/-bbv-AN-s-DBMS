package classes.metadata;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
            List<String> columnNames
    ) {
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
        // TODO: Implement
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
        // TODO: Implement
    }

    public List<String> getReferencedColumnNames() {
        return Collections.emptyList();
    }

    public void setReferencedColumnNames(List<String> referencedColumnNames) {
        // TODO: Implement
    }

    public String getCheckExpression() {
        return checkExpression;
    }

    public void setCheckExpression(String checkExpression) {
        // TODO: Implement
    }

    public boolean validate(Row row) {
        return false;
    }

    public boolean validatePrimaryKey(
            Row row,
            Set<List<Object>> existingKeys
    ) {
        return false;
    }

    public boolean validateUnique(
            Row row,
            Set<List<Object>> existingValues
    ) {
        return false;
    }

    public boolean validateNotNull(Row row) {
        return false;
    }

    public boolean validateForeignKey(
            Row row,
            Set<List<Object>> referencedValues
    ) {
        return false;
    }

    public boolean validateCheck(Row row) {
        return false;
    }

    public boolean isValidDefinition() {
        return false;
    }
}