package core.classes.storageengine;

public class BufferFrame {
    Page page;
    Boolean pinned;
    Boolean dirty;
    long lastAccessTime;
    Boolean referenceBit;
}
