package classes.storageengine;
import java.util.List;

import strategy.IStorageAllocationStrategy;


public class StorageAllocator {

    private List<StorageBlock> blocks;
    private IStorageAllocationStrategy allocationStrategy;

    public StorageAllocator() {
        // TODO: Implement
    }

    public StorageAllocator(
            List<StorageBlock> blocks,
            IStorageAllocationStrategy allocationStrategy
    ) {
        // TODO: Implement
    }

    /**
     * Allocates a storage block with the required size.
     *
     * The allocation strategy is responsible for selecting
     * a suitable free block.
     */
    public StorageBlock allocate(int requiredSize) {
        return null;
    }

    /**
     * Releases an allocated block and makes it available again.
     */
    public void release(StorageBlock block) {
        // TODO: Implement
    }

    /**
     * Changes the storage allocation algorithm at runtime.
     */
    public void setAllocationStrategy(
            IStorageAllocationStrategy allocationStrategy
    ) {
        // TODO: Implement
    }

    public IStorageAllocationStrategy getAllocationStrategy() {
        return null;
    }

    public List<StorageBlock> getBlocks() {
        return List.of();
    }

    public int getBlockCount() {
        return 0;
    }

    public int getFreeBlockCount() {
        return 0;
    }

    public int getAllocatedBlockCount() {
        return 0;
    }

    public boolean containsBlock(StorageBlock block) {
        return false;
    }

    public boolean hasAvailableBlock(int requiredSize) {
        return false;
    }

    public void addBlock(StorageBlock block) {
        // TODO: Implement
    }

    public void removeBlock(StorageBlock block) {
        // TODO: Implement
    }

    public void clear() {
        // TODO: Implement
    }
}