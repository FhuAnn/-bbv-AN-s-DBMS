package core.classes.storageengine.adapter;

import core.classes.storageengine.Page;
import core.classes.storageengine.PageHeader;
import java.nio.ByteBuffer;

public class DefaultPageAdapter
        implements PageAdapter {

    public DefaultPageAdapter() {
        // TODO: Implement
    }

    @Override
    public Page toInternalPage(
            ExternalPage externalPage) {
        // TODO: Implement
        return null;
    }

    @Override
    public ExternalPage toExternalPage(
            Page page) {
        // TODO: Implement
        return null;
    }

    private int convertPageId(
            long externalPageId) {
        // TODO: Implement
        return 0;
    }

    private byte[] convertData(
            ByteBuffer content) {
        // TODO: Implement
        return null;
    }

    private ByteBuffer convertContent(
            byte[] data) {
        // TODO: Implement
        return null;
    }

    private PageHeader createPageHeader(
            ExternalPage externalPage) {
        // TODO: Implement
        return null;
    }
}