package classes.storageengine.adapter;
import classes.storageengine.Page;
public interface PageAdapter {

    Page toInternalPage(ExternalPage externalPage);

    ExternalPage toExternalPage(Page page);
}