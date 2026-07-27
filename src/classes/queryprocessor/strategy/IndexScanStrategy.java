package classes.queryprocessor.strategy;

import enums.query.ScanStrategyType;
import interfaces.IExecutionOperator;
import interfaces.query.IScanStrategy;

public class IndexScanStrategy implements IScanStrategy {

    @Override
    public boolean supports(ScanContext context) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'supports'");
    }

    @Override
    public double estimateCost(ScanContext context) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'estimateCost'");
    }

    @Override
    public IExecutionOperator createOperator(ScanContext context) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOperator'");
    }

    @Override
    public ScanStrategyType getType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }
    
    
}
