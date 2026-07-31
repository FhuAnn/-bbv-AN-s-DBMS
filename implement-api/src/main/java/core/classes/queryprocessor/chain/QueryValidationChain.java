package core.classes.queryprocessor.chain;

import java.util.Objects;

// main module
public class QueryValidationChain {

        private final IQueryValidationHandler firstHandler;

        public QueryValidationChain(
                        IQueryValidationHandler firstHandler) {

                this.firstHandler = Objects.requireNonNull(
                                firstHandler,
                                "First handler must not be null");
        }

        public QueryValidationResult validate(
                        QueryValidationContext context) {

                Objects.requireNonNull(
                                context,
                                "Validation context must not be null");

                return firstHandler.validate(context);
        }
}
