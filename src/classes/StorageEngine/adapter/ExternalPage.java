package classes.storageengine.adapter;
import java.nio.ByteBuffer;

public class ExternalPage {

    private long externalPageId;
    private ByteBuffer content;
    private int checksum;
    private int freeSpacePointer;

    public ExternalPage() {
        // TODO: Implement
    }

    public ExternalPage(
            long externalPageId,
            ByteBuffer content,
            int checksum,
            int freeSpacePointer
    ) {
        // TODO: Implement
    }

    public long getExternalPageId() {
        // TODO: Implement
        return 0;
    }

    public ByteBuffer getContent() {
        // TODO: Implement
        return null;
    }

    public int getChecksum() {
        // TODO: Implement
        return 0;
    }

    public int getFreeSpacePointer() {
        // TODO: Implement
        return 0;
    }

    public void setExternalPageId(long externalPageId) {
        // TODO: Implement
    }

    public void setContent(ByteBuffer content) {
        // TODO: Implement
    }

    public void setChecksum(int checksum) {
        // TODO: Implement
    }

    public void setFreeSpacePointer(int freeSpacePointer) {
        // TODO: Implement
    }
}