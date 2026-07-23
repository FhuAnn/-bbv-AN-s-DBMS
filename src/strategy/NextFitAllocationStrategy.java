package strategy;

import java.util.List;

import classes.storageengine.StorageBlock;

public class NextFitAllocationStrategy implements IStorageAllocationStrategy {
    private int lastSearchPosition;

    public NextFitAllocationStrategy() {
        this.lastSearchPosition = 0;
    }

    @Override
    public StorageBlock selectBlock(List<StorageBlock> blocks, int requiredSize) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getLastSearchPosition() {
        return lastSearchPosition;
    }
}
