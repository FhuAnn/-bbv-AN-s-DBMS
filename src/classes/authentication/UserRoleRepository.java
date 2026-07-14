package classes.authentication;

import java.util.ArrayList;
import java.util.List;

public class UserRoleRepository {
    private final List<UserRole> userRoles = new ArrayList<>();

    public List<UserRole> findByUserId(String userId) {
        List<UserRole> result = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            if (userRole.userId.equals(userId)) {
                result.add(userRole);
            }
        }
        return result;
    }

    public void save(UserRole userRole) {
        userRoles.add(userRole);
    }

    public void delete(String userId, String roleId) {
        userRoles.removeIf(userRole -> userRole.userId.equals(userId) && userRole.roleId.equals(roleId));
    }
}
