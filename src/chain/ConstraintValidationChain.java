package chain;

import interfaces.IConstraintValidationHandler;

public class ConstraintValidationChain {
    private IConstraintValidationHandler firstHandler;

    public ConstraintValidationChain() {
        // TODO: Implement
    }

    public ConstraintValidationChain(
            IConstraintValidationHandler firstHandler) {
        // TODO: Implement
    }

    public ConstraintValidationResult validate(
            ConstraintValidationContext context) {
        return null;
    }

    public IConstraintValidationHandler getFirstHandler() {
        return null;
    }
}
