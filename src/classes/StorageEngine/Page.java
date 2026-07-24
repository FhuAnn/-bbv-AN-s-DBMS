package classes.storageengine;

import enums.PageType;

public abstract class Page {
    private int pageId;
    private byte[] data;
    private PageHeader header;
    private boolean isDirty;
    private int pinCount;

    protected Page(
            int pageId,
            int pageSize) {
        // TODO: Implement
    }

    public abstract PageType getPageType();

    public Page(int pageId) {
        this.pageId = pageId;
        this.data = new byte[8192];
        this.header = new PageHeader(pageId);
    }

    public Page(int pageId, byte[] data) {
        this.pageId = pageId;
        this.data = data;
        this.header = new PageHeader(pageId);
    }

    public Page() {
        this.pageId = -1;
        this.data = new byte[8192];
        this.header = new PageHeader(-1);
    }

    public int getPageId() {
        return pageId;
    }

    public int setPageId(int pageId) {
        return 0;
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
    }

    public void unmarkDirty() {
    }

    public void pin() {
    }

    public void unpin() {
    }
}
