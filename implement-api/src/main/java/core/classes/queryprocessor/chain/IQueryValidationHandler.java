package core.classes.queryprocessor.chain;

public interface IQueryValidationHandler {
    IQueryValidationHandler setNext(IQueryValidationHandler next);

    QueryValidationResult validate(QueryValidationContext context);
}
