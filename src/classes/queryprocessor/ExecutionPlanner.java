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
        return null;
    }

    PhysicalPlanTree getPlanFromCache(String sqlHash) {
        return null;
    }

    private ExecutionOperator buildOperator(PhysicalOperatorNode node) {
        return null;
    }
}
