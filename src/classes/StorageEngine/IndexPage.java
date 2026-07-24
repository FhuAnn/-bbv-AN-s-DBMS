package classes.storageengine;

import enums.PageType;

public class IndexPage extends Page {
    private int entryCount;
    private int maxEntries;

    private Integer parentPageId;
    private Integer nextPageId;

    public IndexPage() {
        super(0, 0);

        // TODO: Implement
    }

    public IndexPage(
            int pageId,
            int pageSize) {
        super(pageId, pageSize);

        // TODO: Implement
    }

    @Override
    public PageType getPageType() {
        return null;
    }

    public int getEntryCount() {
        return 0;
    }

    public int getMaxEntries() {
        return 0;
    }

    public Integer getParentPageId() {
        return null;
    }

    public void setParentPageId(Integer parentPageId) {
        // TODO: Implement
    }

    public Integer getNextPageId() {
        return null;
    }

    public void setNextPageId(Integer nextPageId) {
        // TODO: Implement
    }

    public boolean isFull() {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public int getRemainingEntryCapacity() {
        return 0;
    }
}
