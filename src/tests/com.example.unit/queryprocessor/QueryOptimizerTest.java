
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import classes.queryprocessor.InsertStatementNode;
import classes.queryprocessor.InvalidASTException;
import classes.queryprocessor.LiteralNode;
import classes.queryprocessor.LogicalFilter;
import classes.queryprocessor.LogicalGet;
import classes.queryprocessor.LogicalInsert;
import classes.queryprocessor.LogicalOperator;
import classes.queryprocessor.PhysicalFilter;
import classes.queryprocessor.PhysicalPlanTree;
import classes.queryprocessor.PhysicalSeqScan;
import classes.queryprocessor.QueryOptimizer;
import classes.queryprocessor.SelectStatementNode;
import classes.queryprocessor.TableReferenceNode;

class QueryOptimizerTest {

    private final QueryOptimizer optimizer = new QueryOptimizer(null, null, null);

    @Test
    @DisplayName("generateLogicalPlan - SELECT không có WHERE nên ra LogicalGet")
    void generateLogicalPlan_ShouldCreateLogicalGetForSelectWithoutWhere() {
        SelectStatementNode select = new SelectStatementNode();
        select.setFromClause(new TableReferenceNode("Orders", null));

        LogicalOperator plan = optimizer.generateLogicalPlan(select);

        assertInstanceOf(LogicalGet.class, plan);
        assertEquals("Orders", ((LogicalGet) plan).getTableName());
    }

    @Test
    @DisplayName("generateLogicalPlan - SELECT có WHERE nên ra LogicalFilter bao ngoài LogicalGet")
    void generateLogicalPlan_ShouldCreateLogicalFilterForSelectWithWhere() {
        SelectStatementNode select = new SelectStatementNode();
        select.setFromClause(new TableReferenceNode("Orders", null));
        select.setWhereClause(new LiteralNode(true, "BOOLEAN"));

        LogicalOperator plan = optimizer.generateLogicalPlan(select);

        assertInstanceOf(LogicalFilter.class, plan);
        LogicalFilter filter = (LogicalFilter) plan;
        assertEquals(select.getWhereClause(), filter.getPredicate());
        assertEquals(1, filter.getChildren().size());
        assertInstanceOf(LogicalGet.class, filter.getChildren().get(0));
        assertEquals("Orders", ((LogicalGet) filter.getChildren().get(0)).getTableName());
    }

    @Test
    @DisplayName("generateLogicalPlan - INSERT nên map literal values sang một tuple")
    void generateLogicalPlan_ShouldCreateLogicalInsertForInsertStatement() {
        InsertStatementNode insert = new InsertStatementNode();
        insert.setTargetTable(new TableReferenceNode("Orders", null));
        insert.setValues(List.of(
                new LiteralNode(1, "INT"),
                new LiteralNode("north", "STRING"),
                new LiteralNode(9.5d, "DOUBLE")
        ));

        LogicalOperator plan = optimizer.generateLogicalPlan(insert);

        assertInstanceOf(LogicalInsert.class, plan);
        LogicalInsert logicalInsert = (LogicalInsert) plan;
        assertEquals("Orders", logicalInsert.getTableName());
        assertEquals(1, logicalInsert.getRows().size());
        assertEquals(1, logicalInsert.getRows().get(0).getValues().get("c0"));
        assertEquals("north", logicalInsert.getRows().get(0).getValues().get("c1"));
        assertEquals(9.5d, ((Number) logicalInsert.getRows().get(0).getValues().get("c2")).doubleValue(), 0.0001d);
    }

    @Test
    @DisplayName("optimizePhysicalPlan - logical plan nên được chuyển sang physical plan tương ứng")
    void optimizePhysicalPlan_ShouldCreateMatchingPhysicalTree() {
        SelectStatementNode select = new SelectStatementNode();
        select.setFromClause(new TableReferenceNode("Orders", null));
        select.setWhereClause(new LiteralNode(true, "BOOLEAN"));

        LogicalOperator logicalPlan = optimizer.generateLogicalPlan(select);
        PhysicalPlanTree physicalPlan = optimizer.optimizePhysicalPlan(logicalPlan);

        assertInstanceOf(PhysicalFilter.class, physicalPlan.getRoot());
        PhysicalFilter filter = (PhysicalFilter) physicalPlan.getRoot() ;
        assertEquals(select.getWhereClause(), filter.getPredicate());
        assertEquals(1, filter.getChildren().size());
        assertInstanceOf(PhysicalSeqScan.class, filter.getChildren().get(0));
        assertEquals("Orders", ((PhysicalSeqScan) filter.getChildren().get(0)).getTableId());
    }

    @Test
    @DisplayName("generateLogicalPlan - AST không hỗ trợ nên ném InvalidASTException")
    void generateLogicalPlan_ShouldRejectUnsupportedAst() {
        assertThrows(InvalidASTException.class, () -> optimizer.generateLogicalPlan(new UnsupportedAstNode()));
    }

    @Test
    @DisplayName("optimizePhysicalPlan - logical operator không hỗ trợ nên ném InvalidASTException")
    void optimizePhysicalPlan_ShouldRejectUnsupportedLogicalOperator() {
        assertThrows(InvalidASTException.class, () -> optimizer.optimizePhysicalPlan(new UnsupportedLogicalOperator()));
    }

    private static final class UnsupportedAstNode implements interfaces.ASTNode {
        @Override
        public classes.authentication.ASTNodeType getType() {
            return classes.authentication.ASTNodeType.SELECT;
        }

        @Override
        public <T> T accept(interfaces.ASTVisitor<T> visitor) {
            return null;
        }

        @Override
        public String toString() {
            return "UNSUPPORTED";
        }
    }

    private static final class UnsupportedLogicalOperator extends LogicalOperator {
    }
}