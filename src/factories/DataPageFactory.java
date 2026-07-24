package factories;

import classes.storageengine.DataPage;
import classes.storageengine.Page;

public class DataPageFactory implements PageFactory {
    public DataPageFactory() {
        // TODO: Implement
    }

    @Override
    public Page createPage(int pageId, int pageSize) {
        return new DataPage(pageId, pageSize);
    }
}
