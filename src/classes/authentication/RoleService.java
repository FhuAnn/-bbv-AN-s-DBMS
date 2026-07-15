package classes.authentication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.IRoleService;

public class RoleService implements IRoleService {
    private final UserRoleRepository userRoleRepo = new UserRoleRepository();
    private final PermissionRepository permRepo = new PermissionRepository();
    private final Map<String, Role> roles = new HashMap<>();

    public void createRole(String name, List<Permission> permissions) {
        Role role = new Role(java.util.UUID.randomUUID().toString(), name, permissions);
        roles.put(name, role);
        for (Permission permission : permissions) {
            permRepo.save(role.id, permission);
        }
    }

    public void assignRole(String userID, String roleID) {
        Role role = roles.get(roleID);
        if (role == null) {
            role = roles.getOrDefault("admin", null);
        }
        if (role == null) {
            throw new SecurityException("Role not found: " + roleID);
        }
        userRoleRepo.save(new UserRole(userID, role.id, userID));
    }

    public void unassignRole(String userID, String roleID) {
        userRoleRepo.delete(userID, roleID);
    }

    @Override
    public boolean checkAccess(String userId, String resource, String action) {
        for (UserRole userRole : userRoleRepo.findByUserId(userId)) {
            Role role = roles.values().stream().filter(item -> item.id.equals(userRole.roleId)).findFirst().orElse(null);
            if (role == null) {
                continue;
            }
            for (Permission permission : permRepo.findByRoleId(role.id)) {
                if (permission.resource.equalsIgnoreCase(resource) && permission.action.equalsIgnoreCase(action)) {
                    return true;
                }
            }
        }
        return false;
    }
}
