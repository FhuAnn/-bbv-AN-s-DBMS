package classes.queryprocessor.chain;

public interface IQueryPermissionChecker {
    boolean hasPermission(
            String userId,
            String resource,
            String action);
}
