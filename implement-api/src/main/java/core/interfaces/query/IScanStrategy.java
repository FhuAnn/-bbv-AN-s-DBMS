package core.interfaces.query;

import core.classes.queryprocessor.strategy.ScanContext;
import  core.enums.query.ScanStrategyType;
import  core.interfaces.IExecutionOperator;

public interface IScanStrategy {
    boolean supports(ScanContext context);

    double estimateCost(ScanContext context);

    IExecutionOperator createOperator(
            ScanContext context);

    ScanStrategyType getType();
}
