package classes.StorageEngine;

import java.util.HashMap;
import java.util.Map;

public class BufferPool {
    private int poolSize = 1024;
    private Map<Integer, PageFrame> cache = new HashMap<>();

    public PageFrame getPage(int pageId) {
        return null;
    }

    public void evictPage() {
    }
    public void flushDirtyPages() {
    }
}
