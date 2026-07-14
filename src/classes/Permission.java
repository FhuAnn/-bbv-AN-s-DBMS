package classes;

public class Permission {
    final String permId;
    final String permissionName;
    final String resource;
    final String action;
    final String description;

    Permission(String permId, String permissionName, String resource, String action, String description) {
        this.permId = permId;
        this.permissionName = permissionName;
        this.resource = resource;
        this.action = action;
        this.description = description;
    }
}
