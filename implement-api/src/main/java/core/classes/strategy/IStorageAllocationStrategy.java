package core.classes.strategy;

import java.util.List;

import core.classes.storageengine.StorageBlock;

public interface IStorageAllocationStrategy {
    StorageBlock selectBlock(
            List<StorageBlock> blocks, int requiredSize);
}
