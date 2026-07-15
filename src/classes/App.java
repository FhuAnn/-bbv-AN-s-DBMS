package classes;

import java.util.List;

import classes.authentication.Permission;
import classes.queryprocessor.ColumnInfo;
import classes.queryprocessor.ConstraintInfo;
import classes.queryprocessor.ExecutionService;
import classes.queryprocessor.ResultOutput;
import classes.queryprocessor.SchemaInfo;

public class App {
    public static void main(String[] args) {
        ExecutionService service = new ExecutionService();

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

        service.getSessionMgr().createSession("u1", "127.0.0.1", "dev1");
        service.getRoleService().createRole("admin", List.of(new Permission("p1", "select_orders", "Orders", "SELECT", "allow select")));
        service.getRoleService().assignRole("u1", "admin");

        ResultOutput result = service.execute("SELECT * FROM Orders", service.getSessionMgr().latestAuthToken());
        System.out.println(result.payload);
    }
}