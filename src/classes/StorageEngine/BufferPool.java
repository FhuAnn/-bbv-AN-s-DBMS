package classes.storageengine;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import strategy.IPageReplacementStrategy;

public class BufferPool {
    private final int capacity;
    private final DiskManager diskManager;
    private final LinkedHashMap<Long, byte[]> frames;
    private final Map<Long, Boolean> dirtyPages;
    private IPageReplacementStrategy replacementStrategy;
    public BufferPool(int capacity, DiskManager diskManager) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }

        if (diskManager == null) {
            throw new IllegalArgumentException("Disk manager must not be null");
        }

        this.capacity = capacity;
        this.diskManager = diskManager;
        this.frames = new LinkedHashMap<>(capacity, 0.75f, true);
        this.dirtyPages = new LinkedHashMap<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCachedPageCount() {
        return frames.size();
    }

    public boolean containsPage(long pageId) {
        return frames.containsKey(pageId);
    }

    public byte[] fetchPage(long pageId) {
        validatePageId(pageId);

        byte[] cached = frames.get(pageId);

        if (cached != null) {
            return cached.clone();
        }

        ensureCapacity();
        byte[] loaded = diskManager.readPage(pageId);
        frames.put(pageId, loaded.clone());
        dirtyPages.put(pageId, false);
        return loaded.clone();
    }

    public void updatePage(long pageId, byte[] data) {
        validatePageId(pageId);

        if (data == null) {
            throw new IllegalArgumentException("Page data must not be null");
        }

        if (!frames.containsKey(pageId)) {
            fetchPage(pageId);
        }

        frames.put(pageId, data.clone());
        dirtyPages.put(pageId, true);
    }

    public void flushPage(long pageId) {
        validatePageId(pageId);

        byte[] data = frames.get(pageId);

        if (data == null) {
            throw new IllegalArgumentException("Page is not cached: " + pageId);
        }

        if (Boolean.TRUE.equals(dirtyPages.get(pageId))) {
            diskManager.writePage(pageId, data);
            dirtyPages.put(pageId, false);
        }
    }

    public void flushAll() {
        for (Long pageId : frames.keySet()) {
            flushPage(pageId);
        }
    }

    public boolean evictPage(long pageId) {
        validatePageId(pageId);

        if (!frames.containsKey(pageId)) {
            return false;
        }

        flushPage(pageId);
        frames.remove(pageId);
        dirtyPages.remove(pageId);
        return true;
    }

    public boolean isDirty(long pageId) {
        validatePageId(pageId);
        return Boolean.TRUE.equals(dirtyPages.get(pageId));
    }

    public void clear() {
        flushAll();
        frames.clear();
        dirtyPages.clear();
    }

    public Map<Long, byte[]> getFrames() {
        Map<Long, byte[]> copy = new LinkedHashMap<>();
        frames.forEach((id, data) -> copy.put(id, data.clone()));
        return Collections.unmodifiableMap(copy);
    }

    private void ensureCapacity() {
        if (frames.size() < capacity) {
            return;
        }

        Long eldestPageId = frames.keySet().iterator().next();
        evictPage(eldestPageId);
    }

    private void validatePageId(long pageId) {
        if (pageId <= 0) {
            throw new IllegalArgumentException("Page ID must be positive");
        }
    }
}
