

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import classes.authentication.Permission;
import classes.queryprocessor.ColumnInfo;
import classes.queryprocessor.ConstraintInfo;
import classes.queryprocessor.ExecutionService;
import classes.queryprocessor.ResultOutput;
import classes.queryprocessor.SchemaInfo;


public class QueryProcessorIntegrationIT {
    @Test
    public void insertThenSelectShouldFlowThroughExecutionService() {
        ExecutionService service = new ExecutionService();
        registerOrdersTable(service);
        grantOrdersAccess(service);

        service.getSessionMgr().createSession("u1", "127.0.0.1", "dev1");
        String authToken = service.getSessionMgr().latestAuthToken();

        ResultOutput insertResult = service.execute("INSERT INTO Orders VALUES (1, 'north', 9.5)", authToken);
        assertTrue(insertResult.success);
        assertEquals(1, insertResult.rowCount);
        assertEquals("[{\"rowCount\":1}]", insertResult.payload);

        ResultOutput selectResult = service.execute("SELECT * FROM Orders", authToken);
        assertTrue(selectResult.success);
        assertEquals(1, selectResult.rowCount);
        assertEquals("[{\"c0\":1,\"c1\":\"north\",\"c2\":9.5}]", selectResult.payload);
        assertNotNull(selectResult.payload);
    }

    private void registerOrdersTable(ExecutionService service) {
        service.getCatalogManager().registerTable(
                "Orders",
                new SchemaInfo(
                        "Orders",
                        List.of(
                                new ColumnInfo("id", "INT", false),
                                new ColumnInfo("branch_id", "VARCHAR", false),
                                new ColumnInfo("amount", "DOUBLE", false)
                        ),
                        List.of(new ConstraintInfo("PRIMARY_KEY", "id"))
                )
        );
    }

    private void grantOrdersAccess(ExecutionService service) {
        service.getRoleService().createRole(
                "admin",
                List.of(
                        new Permission("perm-select-orders", "select_orders", "Orders", "SELECT", "allow select"),
                        new Permission("perm-insert-orders", "insert_orders", "Orders", "INSERT", "allow insert")
                )
        );
        service.getRoleService().assignRole("u1", "admin");
    }
}