package classes.authentication;

import java.util.ArrayList;
import java.util.List;

public class Role {
    final String id;
    final String roleName;
    final List<Permission> permissions;

    Role(String id, String roleName, List<Permission> permissions) {
        this.id = id;
        this.roleName = roleName;
        this.permissions = new ArrayList<>(permissions);
    }
}
