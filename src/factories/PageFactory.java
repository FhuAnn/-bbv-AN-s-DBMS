package factories;

import classes.storageengine.Page;

public interface PageFactory {
    Page createPage(
            int pageId,
            int pageSize);
}
