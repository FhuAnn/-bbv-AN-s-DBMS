package core.classes.factories;

import core.classes.storageengine.DataPage;
import core.classes.storageengine.Page;

public class DataPageFactory implements PageFactory {
    public DataPageFactory() {
        // TODO: Implement
    }

    @Override
    public Page createPage(int pageId, int pageSize) {
        return new DataPage(pageId, pageSize);
    }
}
