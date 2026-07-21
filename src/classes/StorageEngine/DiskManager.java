package classes.storageengine;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DiskManager {

    private final Map<Long, byte[]> diskPages = new LinkedHashMap<>();
    private boolean open;
    private long nextPageId = 1L;

    public void open() {
        open = true;
    }

    public void close() {
        open = false;
    }

    public boolean isOpen() {
        return open;
    }

    public long allocatePage() {
        ensureOpen();

        long pageId = nextPageId++;
        diskPages.put(pageId, new byte[0]);
        return pageId;
    }

    public void writePage(long pageId, byte[] data) {
        ensureOpen();
        validatePageId(pageId);

        if (data == null) {
            throw new IllegalArgumentException("Page data must not be null");
        }

        if (!diskPages.containsKey(pageId)) {
            throw new IllegalArgumentException("Page does not exist: " + pageId);
        }

        diskPages.put(pageId, data.clone());
    }

    public byte[] readPage(long pageId) {
        ensureOpen();
        validatePageId(pageId);

        byte[] data = diskPages.get(pageId);

        if (data == null) {
            throw new IllegalArgumentException("Page does not exist: " + pageId);
        }

        return data.clone();
    }

    public boolean deallocatePage(long pageId) {
        ensureOpen();
        validatePageId(pageId);
        return diskPages.remove(pageId) != null;
    }

    public boolean containsPage(long pageId) {
        validatePageId(pageId);
        return diskPages.containsKey(pageId);
    }

    public int getPageCount() {
        return diskPages.size();
    }

    public Map<Long, byte[]> getPages() {
        Map<Long, byte[]> copy = new LinkedHashMap<>();
        diskPages.forEach((id, data) -> copy.put(id, data.clone()));
        return Collections.unmodifiableMap(copy);
    }

    private void ensureOpen() {
        if (!open) {
            throw new IllegalStateException("Disk manager is closed");
        }
    }

    private void validatePageId(long pageId) {
        if (pageId <= 0) {
            throw new IllegalArgumentException("Page ID must be positive");
        }
    }
}
