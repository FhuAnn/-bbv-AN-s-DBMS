package classes.authentication;

import java.util.List;

public class Role {
    String id;
    String roleName;
    List<Permission> permissions;

    public Role(String id, String roleName, List<Permission> permissions) {
        this.id = id;
        this.roleName = roleName;
        this.permissions = permissions;
    }
}
