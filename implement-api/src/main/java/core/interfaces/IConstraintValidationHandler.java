package core.interfaces;

import core.classes.chain.ConstraintValidationContext;

public interface IConstraintValidationHandler {

    IConstraintValidationHandler setNext(
            IConstraintValidationHandler next);

    IConstraintValidationHandler validate(
            ConstraintValidationContext context);
}
