package classes.storageengine;

/**
 * Represents a fixed-size database page (typically 8KB)
 */
public class Page {
    public static final int PAGE_SIZE = 8192; // 8KB

    private final int pageId;
    private final byte[] data;
    private final PageHeader header;
    private boolean isDirty;
    private int pinCount; // For buffer pool reference counting

    public Page(int pageId) {
        this.pageId = pageId;
        this.data = new byte[PAGE_SIZE];
        this.header = new PageHeader(pageId);
        this.isDirty = false;
        this.pinCount = 0;
    }


    
    public Page(int pageId, byte[] data) {
        this.pageId = pageId;
        this.data = data;
        this.header = new PageHeader(pageId);
        this.isDirty = false;
        this.pinCount = 0;
    }

     public Page() {
        this.pageId = -1; // Invalid page ID
        this.data = new byte[PAGE_SIZE];
        this.header = new PageHeader(-1);
        this.isDirty = false;
        this.pinCount = 0;


    }

    public int getPageId() {
        return pageId;
    }

    public int setPageId(int pageId) {
        return pageId;
    }

    public byte[] getData() {
        return data;
    }

    public PageHeader getHeader() {
        return header;
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void markDirty() {
        this.isDirty = true;
    }

    public void unmarkDirty() {
        this.isDirty = false;
    }

    public void pin() {
        pinCount++;
    }

    public void unpin() {
        if (pinCount > 0) pinCount--;
    }

    public int getPinCount() {
        return pinCount;
    }
 public void setPinCount(int pinCount) {
        this.pinCount = pinCount;
    }
    public int getFreeSpace() {
        return PAGE_SIZE - header.getFreeSpacePointer();
    }

    public void setFreeSpacePointer(int pointer) {
        header.setFreeSpacePointer(pointer);
    }

    //getter setter 

    public void setDirty(boolean dirty) {
        isDirty = dirty;
    }

    public void setPinCount(Integer pinCount) {
        this.pinCount = pinCount;
    }

    
    
}