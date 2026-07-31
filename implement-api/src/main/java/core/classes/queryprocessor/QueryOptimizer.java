package core.classes.queryprocessor;

import core.classes.queryprocessor.strategy.ScanContext;
import core.classes.queryprocessor.strategy.ScanStrategySelector;
import core.interfaces.IExecutionOperator;

public class QueryOptimizer {
    private final Object catalogManager;
    private final Object costModel;
    private final Object cardinalityEstimator;
    private ScanStrategySelector scanStrategySelector;

    public QueryOptimizer(
            ScanStrategySelector scanStrategySelector) {
        // TODO: Implement
        catalogManager = null;
        costModel = null;
        cardinalityEstimator = null;
        this.scanStrategySelector = scanStrategySelector;
    }

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

    public IExecutionOperator createScanOperator(
            ScanContext context) {
        // TODO: Implement
        return null;
    }

    public ScanStrategySelector getScanStrategySelector() {
        // TODO: Implement
        return null;
    }

    public void setScanStrategySelector(
            ScanStrategySelector selector) {
        // TODO: Implement
    }
}
