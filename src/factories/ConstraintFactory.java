package factories;

import classes.metadata.ConstraintDefinition;
import interfaces.IConstraint;

public interface ConstraintFactory {
    IConstraint create(ConstraintDefinition definition);
}
