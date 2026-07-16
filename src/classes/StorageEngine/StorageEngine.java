package classes.StorageEngine;

public class StorageEngine {
    private BufferPool bufferPool = new BufferPool();
    private DiskManager diskManager = new DiskManager();
    private RecordManager recordManager = new RecordManager();
    private LogManager logManager = new LogManager();

    public Page readPage(int pageId) {
        return null;
    }

    public void writePage(Page page) {
    }
}