package classes.authentication;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class SecurityManager {

    private final Map<String, Set<String>> rolePermissions = new LinkedHashMap<>();
    private final Map<String, Set<String>> userRoles = new LinkedHashMap<>();

    public void createRole(String role) {
        validate(role, "Role");
        rolePermissions.putIfAbsent(role, new LinkedHashSet<>());
    }

    public void grantPermission(String role, String permission) {
        validate(role, "Role");
        validate(permission, "Permission");

        Set<String> permissions = rolePermissions.get(role);

        if (permissions == null) {
            throw new IllegalArgumentException("Role does not exist");
        }

        permissions.add(permission);
    }

    public boolean revokePermission(String role, String permission) {
        validate(role, "Role");
        validate(permission, "Permission");

        Set<String> permissions = rolePermissions.get(role);
        return permissions != null && permissions.remove(permission);
    }

    public void assignRole(String username, String role) {
        validate(username, "Username");
        validate(role, "Role");

        if (!rolePermissions.containsKey(role)) {
            throw new IllegalArgumentException("Role does not exist");
        }

        userRoles.computeIfAbsent(username, ignored -> new LinkedHashSet<>()).add(role);
    }

    public boolean removeRole(String username, String role) {
        validate(username, "Username");
        validate(role, "Role");

        Set<String> roles = userRoles.get(username);
        return roles != null && roles.remove(role);
    }

    public boolean hasPermission(String username, String permission) {
        validate(username, "Username");
        validate(permission, "Permission");

        Set<String> roles = userRoles.getOrDefault(username, Set.of());

        for (String role : roles) {
            if (rolePermissions.getOrDefault(role, Set.of()).contains(permission)) {
                return true;
            }
        }

        return false;
    }

    public int getRoleCount() {
        return rolePermissions.size();
    }

    public Set<String> getUserRoles(String username) {
        validate(username, "Username");
        return Collections.unmodifiableSet(
                new LinkedHashSet<>(userRoles.getOrDefault(username, Set.of())));
    }

    public Map<String, Set<String>> getRolePermissions() {
        Map<String, Set<String>> copy = new LinkedHashMap<>();
        rolePermissions.forEach(
                (role, permissions) -> copy.put(role, Collections.unmodifiableSet(new LinkedHashSet<>(permissions))));
        return Collections.unmodifiableMap(copy);
    }

    private void validate(String value, String label) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(label + " must not be blank");
        }
    }
}
