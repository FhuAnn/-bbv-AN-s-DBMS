package core.classes.strategy;

import java.util.List;

import core.classes.storageengine.StorageBlock;

public class BestFitAllocationStrategy implements IStorageAllocationStrategy {
    public BestFitAllocationStrategy() {
        // TODO: Implement
    }

    @Override
    public StorageBlock selectBlock(List<StorageBlock> blocks, int requiredSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectBlock'");
    }

}
