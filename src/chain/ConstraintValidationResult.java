package chain;

import classes.abstraction.Constraint;

public class ConstraintValidationResult {

    private boolean valid;
    private Constraint violatedConstraint;
    private String errorCode;
    private String errorMessage;

    public ConstraintValidationResult() {
        // TODO: Implement
    }

    public ConstraintValidationResult(
            boolean valid,
            Constraint violatedConstraint,
            String errorCode,
            String errorMessage) {
        // TODO: Implement
    }

    public static ConstraintValidationResult success() {
        return null;
    }

    public static ConstraintValidationResult failure(
            Constraint violatedConstraint,
            String errorCode,
            String errorMessage) {
        return null;
    }

    public boolean isValid() {
        return false;
    }

    public boolean isInvalid() {
        return false;
    }

    public Constraint getViolatedConstraint() {
        return null;
    }

    public String getErrorCode() {
        return null;
    }

    public String getErrorMessage() {
        return null;
    }

    public boolean hasViolation() {
        return false;
    }
}
