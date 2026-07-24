package classes.storageengine;

import enums.PageType;

public class LogPage extends Page {
    private long firstLSN;
    private long lastLSN;

    private int logRecordCount;
    private int writePosition;

    public LogPage() {
        super(0, 0);

        // TODO: Implement
    }

    public LogPage(
            int pageId,
            int pageSize) {
        super(pageId, pageSize);

        // TODO: Implement
    }

    @Override
    public PageType getPageType() {
        return null;
    }

    public long getFirstLSN() {
        return 0L;
    }

    public void setFirstLSN(long firstLSN) {
        // TODO: Implement
    }

    public long getLastLSN() {
        return 0L;
    }

    public void setLastLSN(long lastLSN) {
        // TODO: Implement
    }

    public int getLogRecordCount() {
        return 0;
    }

    public int getWritePosition() {
        return 0;
    }

    public boolean hasRemainingSpace(
            int requiredSize) {
        return false;
    }

    public int getRemainingSpace() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isFull() {
        return false;
    }
}
