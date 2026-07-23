package strategy;

import java.util.List;

import classes.storageengine.StorageBlock;

public interface IStorageAllocationStrategy {
    StorageBlock selectBlock(
            List<StorageBlock> blocks, int requiredSize);
}
