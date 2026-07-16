
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import classes.authentication.Permission;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PermissionTest {

    private Permission permission;

    @BeforeEach
    void setUp() {
        // Khởi tạo một đối tượng Permission trước mỗi test case
        permission = new Permission(
                "P01", 
                "Read User", 
                "UserResource", 
                "READ", 
                "Cho phép đọc thông tin user"
        );
    }

    @Test
    @DisplayName("Test khởi tạo constructor thành công")
    void testConstructor() {
        assertEquals("P01", permission.permId);
        assertEquals("Read User", permission.permissionName);
        assertEquals("UserResource", permission.resource);
        assertEquals("READ", permission.action);
        assertEquals("description", permission.description);
    }

    @Test
    @DisplayName("Test tạo permission mới (Hành vi mặc định)")
    void testCreatePermission() {
        List<Permission> permissionsList = new ArrayList<>();
        
        // Hiện tại method đang hardcode return false
        boolean result = permission.createPermission("Write User", "Cho phép ghi", permissionsList);
        
        assertFalse(result, "createPermission mặc định phải trả về false");
    }

    @Test
    @DisplayName("Test chỉnh sửa permission (Hành vi mặc định)")
    void testEditPermission() {
        Permission updates = new Permission("P01", "Updated Name", "UserResource", "WRITE", "Updated description");
        
        // Hiện tại method đang hardcode return false
        boolean result = permission.editPermission("P01", updates);
        
        assertFalse(result, "editPermission mặc định phải trả về false");
    }

    @Test
    @DisplayName("Test gán permission cho Role (Hành vi mặc định)")
    void testAssignPermission() {
        // Hiện tại method đang hardcode return false
        boolean result = permission.assignPermission("ROLE_ADMIN", "P01");
        
        assertFalse(result, "assignPermission mặc định phải trả về false");
    }

    @Test
    @DisplayName("Test lấy thông tin Resource (Hành vi mặc định)")
    void testGetResource() {
        // Hiện tại method đang hardcode return null
        String resource = permission.getResource();
        
        assertNull(resource, "getResource mặc định phải trả về null");
    }

    @Test
    @DisplayName("Test lấy thông tin Action (Hành vi mặc định)")
    void testGetAction() {
        // Hiện tại method đang hardcode return null
        String action = permission.getAction();
        
        assertNull(action, "getAction mặc định phải trả về null");
    }
}