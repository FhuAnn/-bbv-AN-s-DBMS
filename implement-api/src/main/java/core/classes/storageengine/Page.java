package core.classes.storageengine;

import java.util.ArrayList;
import java.util.List;

import core.classes.storageengine.iterator.PageRecordIterator;
import core.enums.PageType;
import core.interfaces.storage.record.IRecordIterator;
import core.classes.storageengine.Record;

public class Page {
    private int pageId;
    private byte[] data;
    private PageHeader header;
    private boolean isDirty;
    private int pinCount;
    private final List<Record> records = new ArrayList<>();

    protected Page(
            int pageId,
            int pageSize) {
        // TODO: Implement

    }

    public PageType getPageType() {
        return null;
    };

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

    public int getRecordCount() {
        return records.size();
    }

    public IRecordIterator iterator() {
        return new PageRecordIterator(this);
    }

    public Record getRecord(int position) {
        return records.get(position);
    }
}
