package classes.storageengine;

import enums.PageType;

public class DataPage extends Page {
    public DataPage(
            int pageId,
            int pageSize) {
        super(pageId, pageSize);
                
        // TODO: Implement
    }

    @Override
    public PageType getPageType() {
        return null;
    }
}
