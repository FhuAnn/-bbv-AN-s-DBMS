package core.interfaces;

import java.util.UUID;

import core.enums.ConstraintType;


public interface IConstraint  {
    UUID getId();

    String getName();

    ConstraintType getType();

    boolean validateDefinition();

    void rename(String newName);
}
