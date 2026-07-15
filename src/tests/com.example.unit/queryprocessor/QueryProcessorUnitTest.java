import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import classes.authentication.RoleService;
import classes.queryprocessor.ASTBuildResult;
import classes.queryprocessor.BufferPoolManager;
import classes.queryprocessor.CatalogManager;
import classes.queryprocessor.ColumnInfo;
import classes.queryprocessor.ConstraintInfo;
import classes.queryprocessor.ExecutionPlanner;
import classes.queryprocessor.InsertExecutionOperator;
import classes.queryprocessor.InsertStatementNode;
import classes.queryprocessor.ParserService;
import classes.queryprocessor.PhysicalInsert;
import classes.queryprocessor.PhysicalPlanTree;
import classes.queryprocessor.PhysicalSeqScan;
import classes.queryprocessor.PlanCacheManager;
import classes.queryprocessor.QueryValidation;
import classes.queryprocessor.SchemaInfo;
import classes.queryprocessor.SelectStatementNode;
import classes.queryprocessor.SequentialScanOperator;
import classes.queryprocessor.Tuple;
import interfaces.ExecutionOperator;

public class QueryProcessorUnitTest {
    @Test
    public void parserShouldParseSelectAndInsertStatements() {
        ParserService parser = new ParserService();

        ASTBuildResult selectResult = parser.parserSQL("SELECT * FROM Orders");
        assertTrue(selectResult.success);
        assertInstanceOf(SelectStatementNode.class, selectResult.root);

        SelectStatementNode select = (SelectStatementNode) selectResult.root;
        assertEquals("Orders", select.getFromClause().getTableName());
        assertEquals(1, select.getProjectionList().size());

        ASTBuildResult insertResult = parser.parserSQL("INSERT INTO Orders VALUES (1, 'north', 9.5)");
        assertTrue(insertResult.success);
        assertInstanceOf(InsertStatementNode.class, insertResult.root);

        InsertStatementNode insert = (InsertStatementNode) insertResult.root;
        assertEquals("Orders", insert.getTargetTable().getTableName());
        assertEquals(3, insert.getValues().size());
        assertEquals(1, insert.getValues().get(0).getValue());
        assertEquals("north", insert.getValues().get(1).getValue());
        assertEquals(9.5d, ((Number) insert.getValues().get(2).getValue()).doubleValue(), 0.0001d);
    }

    @Test
    public void validationShouldRejectUnauthorizedTableAccess() {
        CatalogManager catalog = new CatalogManager();
        catalog.registerTable("Orders", ordersSchema());

        QueryValidation validation = new QueryValidation(new RoleService(), catalog);
        ASTBuildResult astBuild = new ParserService().parserSQL("SELECT * FROM Orders");

        assertThrows(SecurityException.class, () -> validation.validateQuery(astBuild, "u1"));
    }

    @Test
    public void plannerShouldBuildExecutionOperatorsFromPhysicalPlans() {
        ExecutionPlanner planner = new ExecutionPlanner(new PlanCacheManager(), new BufferPoolManager());

        PhysicalPlanTree insertPlan = new PhysicalPlanTree(new PhysicalInsert("Orders", List.of(singleRow())));
       ExecutionOperator insertOperator = planner.buildExecutionTree(insertPlan);
        assertInstanceOf(InsertExecutionOperator.class, insertOperator);

        PhysicalPlanTree scanPlan = new PhysicalPlanTree(new PhysicalSeqScan("Orders"));
        ExecutionOperator scanOperator = planner.buildExecutionTree(scanPlan);
        assertInstanceOf(SequentialScanOperator.class, scanOperator);
    }

    @Test
    public void planCacheShouldStoreAndReturnSamePlan() {
        PlanCacheManager cacheManager = new PlanCacheManager();
        PhysicalPlanTree plan = new PhysicalPlanTree(new PhysicalSeqScan("Orders"));

        cacheManager.put("hash-1", plan);

        assertNotNull(cacheManager.get("hash-1"));
        assertEquals(plan, cacheManager.get("hash-1"));
    }

    private SchemaInfo ordersSchema() {
        return new SchemaInfo(
                "Orders",
                List.of(
                        new ColumnInfo("id", "INT", false),
                        new ColumnInfo("branch_id", "VARCHAR", false),
                        new ColumnInfo("amount", "DOUBLE", false)
                ),
                List.of(new ConstraintInfo("PRIMARY_KEY", "id"))
        );
    }

    private Tuple singleRow() {
        Tuple tuple = new Tuple();
        tuple.getValues().put("c0", 1);
        tuple.getValues().put("c1", "north");
        tuple.getValues().put("c2", 9.5d);
        return tuple;
    }
}