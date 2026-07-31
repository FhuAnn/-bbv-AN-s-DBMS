package core.classes.queryprocessor.chain;

import java.util.Objects;

public class PermissionValidationHandler
        extends AbstractQueryValidationHandler {

    private final IQueryPermissionChecker permissionChecker;

    public PermissionValidationHandler(
            IQueryPermissionChecker permissionChecker) {

        this.permissionChecker = Objects.requireNonNull(
                permissionChecker,
                "Permission checker must not be null");
    }

    @Override
    protected QueryValidationResult doValidate(
            QueryValidationContext context) {

        String userId = context.getUserId();
        String resource = context.getResourceName();
        String action = context.getRequiredAction();

        if (action == null || action.isBlank()) {
            return QueryValidationResult.failure(
                    getClass().getSimpleName(),
                    "Required permission action is missing");
        }

        boolean allowed = permissionChecker.hasPermission(
                userId,
                resource,
                action);

        if (!allowed) {
            return QueryValidationResult.failure(
                    getClass().getSimpleName(),
                    "User "
                            + userId
                            + " does not have "
                            + action
                            + " permission on "
                            + resource);
        }

        return QueryValidationResult.success();
    }
}
