package classes.storageengine;

public class PageHeader {
    private int pageId;
    private int freeSpacePointer;
    private int slotCount;
    private int checksum;

    public PageHeader(int pageId) {
        this.pageId = pageId;
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
    }

    public int getChecksum() {
        return checksum;
    }

    public void updateChecksum() {
    }
}
