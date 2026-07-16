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

        return null;

    }



    public LogicalOperator optimizeLogicalPlan(LogicalOperator logicalPlan) {

        return null;

    }



    public PhysicalPlanTree optimizePhysicalPlan(LogicalOperator logicalPlan) {

        return null;

    }



    private PhysicalOperatorNode toPhysical(LogicalOperator logicalPlan) {

        return null;

    }



    private List<Tuple> toRows(List<LiteralNode> literalNodes) {

        return null;

    }

}