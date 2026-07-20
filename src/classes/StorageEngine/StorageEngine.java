package classes.storageengine;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class StorageEngine {
    public enum State {
        CLOSED, OPEN
    }

    private final UUID id = UUID.randomUUID();
    private final Map<Long, byte[]> pages = new LinkedHashMap<>();
    private State state = State.CLOSED;
    private long nextPageId = 1L;

    public UUID getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    public boolean isOpen() {
        return state == State.OPEN;
    }

    public void open() {
        state = State.OPEN;
    }

    public void close() {
        state = State.CLOSED;
    }

    public long allocatePage() {
        ensureOpen();
        long pageId = nextPageId++;
        pages.put(pageId, new byte[0]);
        return pageId;
    }

    public void writePage(long pageId, byte[] data) {
        ensureOpen();
        validatePageId(pageId);
        if (data == null)
            throw new IllegalArgumentException("Page data must not be null");
        if (!pages.containsKey(pageId))
            throw new IllegalArgumentException("Page does not exist");
        pages.put(pageId, data.clone());
    }

    public byte[] readPage(long pageId) {
        ensureOpen();
        validatePageId(pageId);
        byte[] data = pages.get(pageId);
        if (data == null)
            throw new IllegalArgumentException("Page does not exist");
        return data.clone();
    }

    public boolean deletePage(long pageId) {
        ensureOpen();
        validatePageId(pageId);
        return pages.remove(pageId) != null;
    }

    public boolean containsPage(long pageId) {
        validatePageId(pageId);
        return pages.containsKey(pageId);
    }

    public int getPageCount() {
        return pages.size();
    }

    public boolean isEmpty() {
        return pages.isEmpty();
    }

    public void clear() {
        ensureOpen();
        pages.clear();
    }

    public Map<Long, byte[]> getPages() {
        Map<Long, byte[]> copy = new LinkedHashMap<>();
        pages.forEach((id, data) -> copy.put(id, data.clone()));
        return Collections.unmodifiableMap(copy);
    }

    private void ensureOpen() {
        if (!isOpen())
            throw new IllegalStateException("Storage engine is closed");
    }

    private void validatePageId(long pageId) {
        if (pageId <= 0)
            throw new IllegalArgumentException("Page ID must be positive");
    }
}
