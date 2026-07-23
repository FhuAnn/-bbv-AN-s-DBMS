package strategy;

import java.util.List;

import classes.storageengine.StorageBlock;

public class FirstFitAllocationStrategy implements IStorageAllocationStrategy {
    public FirstFitAllocationStrategy() {
    }

    @Override
    public StorageBlock selectBlock(List<StorageBlock> blocks, int requiredSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectBlock'");
    }
}
