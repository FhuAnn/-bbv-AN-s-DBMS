package interfaces.query;

import classes.queryprocessor.strategy.ScanContext;
import enums.query.ScanStrategyType;
import interfaces.IExecutionOperator;

public interface IScanStrategy {
    boolean supports(ScanContext context);

    double estimateCost(ScanContext context);

    IExecutionOperator createOperator(
            ScanContext context);

    ScanStrategyType getType();
}
