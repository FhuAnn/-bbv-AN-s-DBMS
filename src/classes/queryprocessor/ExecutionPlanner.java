package classes.queryprocessor;

import interfaces.ExecutionOperator;

public class ExecutionPlanner {
    private final PlanCacheManager cacheManager;
    private final BufferPoolManager bufferPoolManager;

    public ExecutionPlanner(PlanCacheManager cacheManager, BufferPoolManager bufferPoolManager) {
        this.cacheManager = cacheManager;
        this.bufferPoolManager = bufferPoolManager;
    }

    public ExecutionOperator buildExecutionTree(PhysicalPlanTree plan) {
        return buildOperator(plan.root);
    }

    PhysicalPlanTree getPlanFromCache(String sqlHash) {
        return cacheManager.get(sqlHash);
    }

    private ExecutionOperator buildOperator(PhysicalOperatorNode node) {
        if (node instanceof PhysicalInsert insert) {
            return new InsertExecutionOperator(insert.tableId, insert.rows, bufferPoolManager);
        }
        if (node instanceof PhysicalFilter filter) {
            ExecutionOperator child = buildOperator(filter.children.get(0));
            return new FilterOperator(child, filter.predicate);
        }
        if (node instanceof PhysicalSeqScan seqScan) {
            return new SequentialScanOperator(seqScan.tableId, bufferPoolManager);
        }
        if (node instanceof PhysicalIndexScan indexScan) {
            return new IndexScanOperator(indexScan.indexId, indexScan.scanKey, bufferPoolManager);
        }
        throw new InvalidASTException("Unsupported physical operator: " + node.getClass().getSimpleName());
    }
}
