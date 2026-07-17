package classes.storageengine;

import java.io.IOException;

public class DiskManager {
    private final FileManager fileManager;

    public DiskManager() {
        this.fileManager = new FileManager();
    }

    public DiskManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void writePage(String fileName, int pageId, Page page) throws IOException {
    }

    public Page readPage(String fileName, int pageId) throws IOException {
        return null;
    }

    public int allocateNewPage(String fileName) throws IOException {
        return 0;
    }
}
