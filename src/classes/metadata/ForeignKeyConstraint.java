package classes.metadata;

import enums.ReferentialAction;

import java.util.UUID;

public class ForeignKeyConstraint extends Constraint {
    public UUID referenceTableId;
    public UUID referenceColumnId;
    public ReferentialAction onDelete;
    public ReferentialAction onUpdate;
}