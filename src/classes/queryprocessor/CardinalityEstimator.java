package classes.queryprocessor;

import classes.authentication.ExpressionNode;

public class CardinalityEstimator {
    double estimateSelectivity(ExpressionNode predicate, TableStatistics stats) {
        return 0.0d;
    }
}
