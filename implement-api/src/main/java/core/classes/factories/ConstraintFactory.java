package core.classes.factories;

import core.classes.abstraction.Constraint;
import core.classes.metadata.definition.ConstraintDefinition;

public interface ConstraintFactory {
    Constraint create(ConstraintDefinition definition);
}
