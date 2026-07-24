package chain;

import interfaces.IConstraintValidationHandler;

public abstract class AbstractConstraintValidationHandler
        implements IConstraintValidationHandler {

    private IConstraintValidationHandler next;

    protected AbstractConstraintValidationHandler() {
        // TODO: Implement
    }

    @Override
    public IConstraintValidationHandler setNext(
            IConstraintValidationHandler next
    ) {
        return null;
    }

    @Override
    public IConstraintValidationHandler validate(
            ConstraintValidationContext context
    ) {
        return null;
    }

    protected abstract ConstraintValidationResult doValidate(
            ConstraintValidationContext context
    );

    protected ConstraintValidationResult validateNext(
            ConstraintValidationContext context
    ) {
        return null;
    }

    public IConstraintValidationHandler getNext() {
        return null;
    }
}
