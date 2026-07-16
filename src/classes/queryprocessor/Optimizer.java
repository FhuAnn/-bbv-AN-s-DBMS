package classes.queryprocessor;

public class Optimizer {
    public LogicalOptimizer logical;
    public PhysicalOptimizer physical;
    public CardinalityEstimator estimator;
    public CostModel costModel;
}