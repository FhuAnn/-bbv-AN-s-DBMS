package classes.queryprocessor.strategy;

import java.util.List;

import interfaces.query.IScanStrategy;

public class ScanStrategySelector {
    private List<IScanStrategy> strategies;

    public ScanStrategySelector(
            List<IScanStrategy> strategies) {
        // TODO: Implement

        this.strategies = null;
    }

    public IScanStrategy select(
            ScanContext context) {
        // TODO: Implement
        return null;
    }

    public void register(
            IScanStrategy strategy) {
        // TODO: Implement
    }

    public List<IScanStrategy> getStrategies() {
        // TODO: Implement
        return List.of();
    }
}
