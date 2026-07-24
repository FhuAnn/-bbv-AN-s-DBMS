package interfaces;

import chain.ConstraintValidationContext;

public interface IConstraintValidationHandler {

    IConstraintValidationHandler setNext(
            IConstraintValidationHandler next);

    IConstraintValidationHandler validate(
            ConstraintValidationContext context);
}
