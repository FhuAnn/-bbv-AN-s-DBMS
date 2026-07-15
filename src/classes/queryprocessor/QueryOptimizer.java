package classes.queryprocessor;

import java.util.ArrayList;
import java.util.List;

import interfaces.ASTNode;

public class QueryOptimizer {
    private final CatalogManager catalogManager;
    private final CostModel costModel;
    private final CardinalityEstimator cardinalityEstimator;

    public QueryOptimizer(CatalogManager catalogManager, CostModel costModel, CardinalityEstimator cardinalityEstimator) {
        this.catalogManager = catalogManager;
        this.costModel = costModel;
        this.cardinalityEstimator = cardinalityEstimator;
    }

    public LogicalOperator generateLogicalPlan(ASTNode ast) {
        if (ast instanceof SelectStatementNode selectStatement) {
            if (selectStatement.whereClause != null) {
                LogicalFilter filter = new LogicalFilter(selectStatement.whereClause);
                filter.children.add(new LogicalGet(selectStatement.fromClause.tableName));
                return filter;
            }
            return new LogicalGet(selectStatement.fromClause.tableName);
        }
        if (ast instanceof InsertStatementNode insertStatement) {
            return new LogicalInsert(insertStatement.targetTable.tableName, toRows(insertStatement.values));
        }
        throw new InvalidASTException("Unsupported AST type: " + ast.getClass().getSimpleName());
    }

    public LogicalOperator optimizeLogicalPlan(LogicalOperator logicalPlan) {
        return logicalPlan;
    }

    public PhysicalPlanTree optimizePhysicalPlan(LogicalOperator logicalPlan) {
        return new PhysicalPlanTree(toPhysical(logicalPlan));
    }

    private PhysicalOperatorNode toPhysical(LogicalOperator logicalPlan) {
        if (logicalPlan instanceof LogicalInsert insert) {
            return new PhysicalInsert(insert.tableName, insert.rows);
        }
        if (logicalPlan instanceof LogicalFilter filter) {
            PhysicalFilter physicalFilter = new PhysicalFilter(filter.predicate);
            physicalFilter.children.add(toPhysical(filter.children.get(0)));
            return physicalFilter;
        }
        if (logicalPlan instanceof LogicalGet get) {
            return new PhysicalSeqScan(get.tableName);
        }
        throw new InvalidASTException("Unsupported logical operator: " + logicalPlan.getClass().getSimpleName());
    }

    private List<Tuple> toRows(List<LiteralNode> literalNodes) {
        List<Tuple> rows = new ArrayList<>();
        Tuple tuple = new Tuple();
        for (int index = 0; index < literalNodes.size(); index++) {
            tuple.values.put("c" + index, literalNodes.get(index).value);
        }
        rows.add(tuple);
        return rows;
    }
}