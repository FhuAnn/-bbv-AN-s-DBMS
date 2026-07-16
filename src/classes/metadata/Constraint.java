package classes.metadata;

import enums.ConstraintType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Constraint {
    public UUID id;
    public ConstraintType type;
    public List<UUID> columnIds = new ArrayList<>();

    public Constraint() {
        this.id = UUID.randomUUID();
    }
}