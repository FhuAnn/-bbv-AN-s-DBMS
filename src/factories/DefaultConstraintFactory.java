package factories;

import classes.abstraction.Constraint;
import classes.metadata.definition.ConstraintDefinition;

public class DefaultConstraintFactory implements ConstraintFactory {
    @Override
    public Constraint create(ConstraintDefinition definition) {
        // Implement the logic to create a constraint based on the definition
        return switch (definition.getType()) {
            case PRIMARY_KEY -> createPrimaryKey(definition);
            case FOREIGN_KEY -> createForeignKey(definition);
            case UNIQUE -> createUnique(definition);
            case CHECK -> createCheck(definition);
            case NOT_NULL -> createNotNull(definition);
        };
    }

    private Constraint createPrimaryKey(
            ConstraintDefinition definition) {
        return null;
    }

    private Constraint createForeignKey(
            ConstraintDefinition definition) {

        return null;
    }

    private Constraint createUnique(
            ConstraintDefinition definition) {
        return null;
    }

    private Constraint createCheck(
            ConstraintDefinition definition) {
        return null;
    }

    private Constraint createNotNull(
            ConstraintDefinition definition) {
        return null;
    }
}
