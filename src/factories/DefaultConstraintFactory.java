package factories;

import classes.metadata.ConstraintDefinition;

import interfaces.IConstraint;

public class DefaultConstraintFactory implements ConstraintFactory {
    @Override
    public IConstraint create(ConstraintDefinition definition) {
        // Implement the logic to create a constraint based on the definition
        return switch (definition.getType()) {
            case PRIMARY_KEY -> createPrimaryKey(definition);
            case FOREIGN_KEY -> createForeignKey(definition);
            case UNIQUE -> createUnique(definition);
            case CHECK -> createCheck(definition);
            case NOT_NULL -> createNotNull(definition);
        };
    }

    private IConstraint createPrimaryKey(
            ConstraintDefinition definition) {
        return null;
    }

    private IConstraint createForeignKey(
            ConstraintDefinition definition) {

        return null;
    }

    private IConstraint createUnique(
            ConstraintDefinition definition) {
        return null;
    }

    private IConstraint createCheck(
            ConstraintDefinition definition) {
        return null;
    }

    private IConstraint createNotNull(
            ConstraintDefinition definition) {
        return null;
    }
}
