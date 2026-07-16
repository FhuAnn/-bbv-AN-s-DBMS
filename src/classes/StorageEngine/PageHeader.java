package classes.storageengine;

/**
 * Header metadata for each database page
 */
public class PageHeader {
    private final int pageId;
    private int freeSpacePointer;   // Points to start of free space
    private int slotCount;          // Number of record slots used
    private int checksum;           // For data integrity

    public PageHeader(int pageId) {
        this.pageId = pageId;
        this.freeSpacePointer = 0;
        this.slotCount = 0;
        this.checksum = 0;
    }

    public int getPageId() {
        return pageId;
    }

    public int getFreeSpacePointer() {
        return freeSpacePointer;
    }

    public void setFreeSpacePointer(int freeSpacePointer) {
        this.freeSpacePointer = freeSpacePointer;
    }

    public int getSlotCount() {
        return slotCount;
    }

    public void incrementSlotCount() {
        this.slotCount++;
    }

    public int getChecksum() {
        return checksum;
    }

    public void updateChecksum() {
        // Simple checksum calculation in real impl
        this.checksum = 0; // placeholder
    }
}