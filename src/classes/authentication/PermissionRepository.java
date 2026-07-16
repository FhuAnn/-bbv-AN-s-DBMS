package classes.authentication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionRepository {
    private final Map<String, List<Permission>> permissionsByRole = new HashMap<>();

    List<Permission> findByRoleId(String roleId) {
        return null;
    }

    void save(String roleId, Permission permission) {
    }
}
