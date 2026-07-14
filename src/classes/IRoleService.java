package classes;

public interface IRoleService {
    boolean checkAccess(String userId, String resource, String action);
}
