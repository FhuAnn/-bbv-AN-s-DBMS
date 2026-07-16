package classes.authentication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RoleService  {
    private final UserRoleRepository userRoleRepo = new UserRoleRepository();
    private final PermissionRepository permRepo = new PermissionRepository();
    private final Map<String, Role> roles = new HashMap<>();

    public void createRole(String name, List<Permission> permissions) {
    }

    public void assignRole(String userID, String roleID) {
    }

    public void unassignRole(String userID, String roleID) {
    }

    public boolean checkAccess(String userId, String resource, String action) {
        return false;
    }
}
