package classes.queryprocessor;

public class QueryOptimizer {
    private final Object catalogManager;
    private final Object costModel;
    private final Object cardinalityEstimator;

    public QueryOptimizer(Object catalogManager, Object costModel, Object cardinalityEstimator) {
        this.catalogManager = catalogManager;
        this.costModel = costModel;
        this.cardinalityEstimator = cardinalityEstimator;
    }

    public Object generateLogicalPlan(ASTBuildResult ast) {
        return null;
    }

    public Object optimizeLogicalPlan(Object logicalPlan) {
        return null;
    }

    public Object optimizePhysicalPlan(Object logicalPlan) {
        return null;
    }
}
