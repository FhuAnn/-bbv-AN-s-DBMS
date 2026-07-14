package classes.authentication;

import java.util.List;

public class Permission {
    public final String permId;
    public final String permissionName;
    public final String resource;
    public final String action;
    public final String description;

    public Permission(String permId, String permissionName, String resource, String action, String description) {
        this.permId = permId;
        this.permissionName = permissionName;
        this.resource = resource;
        this.action = action;
        this.description = description;
    }
    public boolean createPermission(String name, String description, List<Permission> permissions) {
        // Implementation for creating a new permission
        return true;
    }
    public boolean editPermission(String permId, Permission updates) {
        // Implementation for editing a permission
        return true;
    }

    public boolean assignPermission(String roleId, String permId) {
        // Implementation for assigning a permission to a role
        return true;
    }
}
