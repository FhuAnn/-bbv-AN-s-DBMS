import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import classes.authentication.Permission;
import classes.authentication.RoleService;

public class RoleServiceTest {
    @Test
    public void createRoleAndAssignRoleShouldGrantAccess() {
        RoleService roleService = new RoleService();
        roleService.createRole("admin", List.of(new Permission("p1", "select_orders", "Orders", "SELECT", "allow select")));

        roleService.assignRole("u1", "admin");

        assertTrue(roleService.checkAccess("u1", "Orders", "SELECT"));
    }

    @Test
    public void checkAccessShouldReturnFalseWhenPermissionDoesNotMatch() {
        RoleService roleService = new RoleService();
        roleService.createRole("admin", List.of(new Permission("p1", "select_orders", "Orders", "SELECT", "allow select")));
        roleService.assignRole("u1", "admin");

        assertFalse(roleService.checkAccess("u1", "Orders", "DELETE"));
    }

    @Test
    public void assignRoleShouldThrowWhenRoleDoesNotExist() {
        RoleService roleService = new RoleService();

        assertThrows(SecurityException.class, () -> roleService.assignRole("u1", "missing-role"));
    }
}