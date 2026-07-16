package classes.storageengine;

public class PageFrame {
    public Page page;
    public boolean dirty;
    public int pinCount;

    public PageFrame(Page page) {
        this.page = page;
    }

    public PageFrame() {
        this.page = null;
        this.dirty = false;
        this.pinCount = 0;
    }

    //getter + setter 
    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public int getPinCount() {
        return pinCount;
    }

    public void setPinCount(int pinCount) {
        this.pinCount = pinCount;
    }

    public boolean isPinned() {
        return pinCount > 0;
    }

    public void setPinned(boolean pinned) {
        if (pinned) {
            this.pinCount++;
        } else {
            if (this.pinCount > 0) {
                this.pinCount--;
            }
        }
    }
}