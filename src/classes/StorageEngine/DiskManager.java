package classes.storageengine;

import java.io.IOException;
import java.io.RandomAccessFile;

public class DiskManager {
    private final FileManager fileManager;

    
    public DiskManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void writePage(String fileName, int pageId, Page page) throws IOException {
     
    }

    public Page readPage(String fileName, int pageId) throws IOException {
        
        return null;
    }

    public int allocateNewPage(String fileName) throws IOException {
        RandomAccessFile raf = fileManager.openFile(fileName);
        return (int) (raf.length() / Page.PAGE_SIZE);
    }
}
