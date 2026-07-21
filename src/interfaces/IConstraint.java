package interfaces;

import java.util.UUID;

import enums.ConstraintType;


public interface IConstraint {
    UUID getId();

    String getName();

    ConstraintType getType();

    boolean validateDefinition();
}
