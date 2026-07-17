package classes.authentication;

import java.util.List;

public class Permission {
    public String permId;
    public String permissionName;
    public String resource;
    public String action;
    public String description;

    public Permission(String permId, String permissionName, String resource, String action, String description) {
        this.permId = permId;
        this.permissionName = permissionName;
        this.resource = resource;
        this.action = action;
        this.description = description;
    }

    public boolean createPermission(String name, String description, List<Permission> permissions) {
        return false;
    }

    public boolean editPermission(String permId, Permission updates) {
        return false;
    }

    public boolean assignPermission(String roleId, String permId) {
        return false;
    }

    public String getResource() {
        return null;
    }

    public String getAction() {
        return null;
    }
}
