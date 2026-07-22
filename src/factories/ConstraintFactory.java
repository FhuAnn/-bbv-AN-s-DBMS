package factories;

import classes.abstraction.Constraint;
import classes.metadata.ConstraintDefinition;

public interface ConstraintFactory {
    Constraint create(ConstraintDefinition definition);
}
