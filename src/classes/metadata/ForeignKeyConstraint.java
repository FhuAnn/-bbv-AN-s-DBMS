package classes.metadata;

import enums.ConstraintType;
import enums.ReferentialAction;

import java.util.UUID;

public class ForeignKeyConstraint extends Constraint {
    private UUID referenceTableId;
    private UUID referenceColumnId;
    private ReferentialAction onDelete;
    private ReferentialAction onUpdate;

    public ForeignKeyConstraint(UUID id, java.util.List<UUID> columnIds, UUID referenceTableId, UUID referenceColumnId, ReferentialAction onDelete, ReferentialAction onUpdate) {
        super(id, ConstraintType.FOREIGN_KEY, columnIds);
        this.referenceTableId = referenceTableId;
        this.referenceColumnId = referenceColumnId;
        this.onDelete = onDelete;
        this.onUpdate = onUpdate;
    }

    // Getters and Setters
    public UUID getReferenceTableId() {
        return referenceTableId;
    }

    public void setReferenceTableId(UUID referenceTableId) {
        this.referenceTableId = referenceTableId;
    }

    public UUID getReferenceColumnId() {
        return referenceColumnId;
    }

    public void setReferenceColumnId(UUID referenceColumnId) {
        this.referenceColumnId = referenceColumnId;
    }

    public ReferentialAction getOnDelete() {
        return onDelete;
    }

    public void setOnDelete(ReferentialAction onDelete) {
        this.onDelete = onDelete;
    }

    public ReferentialAction getOnUpdate() {
        return onUpdate;
    }

    public void setOnUpdate(ReferentialAction onUpdate) {
        this.onUpdate = onUpdate;
    }
}