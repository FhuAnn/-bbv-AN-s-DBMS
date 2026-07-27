package classes.queryprocessor.chain;

public final class QueryValidationResult {
    private final boolean valid;
    private final String validator;
    private final String message;

    private QueryValidationResult(
            boolean valid,
            String validator,
            String message) {

        this.valid = valid;
        this.validator = validator;
        this.message = message;
    }

    public static QueryValidationResult success() {
        return new QueryValidationResult(
                true,
                null,
                null);
    }

    public static QueryValidationResult failure(
            String validator,
            String message) {

        return new QueryValidationResult(
                false,
                validator,
                message);
    }

    public boolean isValid() {
        return valid;
    }

    public String getValidator() {
        return validator;
    }

    public String getMessage() {
        return message;
    }
}
