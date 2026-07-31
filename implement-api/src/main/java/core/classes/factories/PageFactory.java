package core.classes.factories;

import core.classes.storageengine.Page;

public interface PageFactory {
    Page createPage(
            int pageId,
            int pageSize);
}
