package factories;

import classes.abstraction.Constraint;
import classes.metadata.definition.ConstraintDefinition;

public interface ConstraintFactory {
    Constraint create(ConstraintDefinition definition);
}
