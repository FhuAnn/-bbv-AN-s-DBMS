
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import classes.authentication.Permission;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PermissionTest {

    private Permission permissionFixture;
    private List<Permission> permissionList;

    @BeforeEach
    void setUp() {
        // Tạo một đối tượng fixture để gọi các hàm instance
        permissionFixture = new Permission("P001", "Read User", "User", "READ", "Allow reading user info");
        permissionList = new ArrayList<>();
        permissionList.add(permissionFixture);
    }

    @Test
    @DisplayName("createPermission - Nên tạo thành công khi dữ liệu hợp lệ")
    void createPermission_Success() {
        boolean result = permissionFixture.createPermission(
            "Write User", 
            "Allow creating user info", 
            permissionList
        );
        
        assertTrue(result, "Hàm sẽ trả về true khi tạo quyền mới thành công");
    }

    @Test
    @DisplayName("editPermission - Nên sửa thành công khi permId tồn tại")
    void editPermission_Success() {
        Permission updatedData = new Permission("P001", "Read User Updated", "User", "READ", "New description");
        
        boolean result = permissionFixture.editPermission("P001", updatedData);
        
        assertTrue(result, "Hàm sẽ trả về true khi cập nhật quyền thành công");
    }

    @Test
    @DisplayName("assignPermission - Nên gán quyền vào role thành công")
    void assignPermission_Success() {
        String roleId = "ROLE_ADMIN";
        String permId = "P001";
        
        boolean result = permissionFixture.assignPermission(roleId, permId);
        
        assertTrue(result, "Hàm sẽ trả về true khi gán quyền vào role thành công");
    }
}