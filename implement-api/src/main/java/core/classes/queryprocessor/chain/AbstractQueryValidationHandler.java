package core.classes.queryprocessor.chain;

import java.util.Objects;

public abstract class AbstractQueryValidationHandler implements IQueryValidationHandler {
    private IQueryValidationHandler next;

    @Override
    public IQueryValidationHandler setNext(
            IQueryValidationHandler next) {

        this.next = Objects.requireNonNull(
                next,
                "Next validation handler must not be null");

        return next;
    }

    @Override
    public QueryValidationResult validate(
            QueryValidationContext context) {

        Objects.requireNonNull(
                context,
                "Query validation context must not be null");

        QueryValidationResult result = doValidate(context);

        if (!result.isValid()) {
            return result;
        }

        return validateNext(context);
    }

    protected abstract QueryValidationResult doValidate(
            QueryValidationContext context);

    protected QueryValidationResult validateNext(
            QueryValidationContext context) {

        if (next == null) {
            return QueryValidationResult.success();
        }

        return next.validate(context);
    }
}
