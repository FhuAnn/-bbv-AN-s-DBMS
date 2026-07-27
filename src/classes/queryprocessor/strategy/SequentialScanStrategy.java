package classes.queryprocessor.strategy;

import enums.query.ScanStrategyType;
import interfaces.IExecutionOperator;
import interfaces.query.IScanStrategy;

public class SequentialScanStrategy implements IScanStrategy {
    @Override
    public boolean supports(ScanContext context) {
        // Implementation for checking if this strategy supports the given context
        return true; // Placeholder implementation
    }

    @Override
    public double estimateCost(ScanContext context) {
        // Implementation for estimating the cost of this strategy
        return 0.0; // Placeholder implementation
    }

    @Override
    public IExecutionOperator createOperator(ScanContext context) {
        // Implementation for creating an execution operator for this strategy
        return null; // Placeholder implementation
    }

    @Override
    public ScanStrategyType getType() {
        return ScanStrategyType.SEQUENTIAL_SCAN;
    }

}
