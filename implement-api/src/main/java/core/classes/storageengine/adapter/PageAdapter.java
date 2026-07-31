package core.classes.storageengine.adapter;
import  core.classes.storageengine.Page;
public interface PageAdapter {

    Page toInternalPage(ExternalPage externalPage);

    ExternalPage toExternalPage(Page page);
}