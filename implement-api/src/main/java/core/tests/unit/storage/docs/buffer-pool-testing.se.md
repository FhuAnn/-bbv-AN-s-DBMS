# BufferPool Testing - Main Functional Sequences

---

## 1. Pin Page

```mermaid
sequenceDiagram
actor Test
participant BufferPool
participant Page

Test->>BufferPool: getPage(pageId)
BufferPool->>Page: pin()
Page-->>BufferPool: pinned
BufferPool-->>Test: Page
```

---

## 2. Unpin Page

```mermaid
sequenceDiagram
actor Test
participant BufferPool
participant Page

Test->>BufferPool: releasePage(pageId)
BufferPool->>Page: unpin()
Page-->>BufferPool: unpinned
BufferPool-->>Test: success
```

---

## 3. Evict Page

```mermaid
sequenceDiagram
actor Test
participant BufferPool
participant Page

Test->>BufferPool: evictPage()
BufferPool->>BufferPool: selectVictim()
BufferPool->>Page: flushDirtyPages()
Page-->>BufferPool: flushed
BufferPool-->>Test: evicted
```

---

## 4. Flush Page

```mermaid
sequenceDiagram
actor Test
participant BufferPool
participant StorageEngine

Test->>BufferPool: flushDirtyPages()
BufferPool->>StorageEngine: writePage(page)
StorageEngine-->>BufferPool: written
BufferPool-->>Test: completed
```

---

## 5. Allocate Frame

```mermaid
sequenceDiagram
actor Test
participant BufferPool

Test->>BufferPool: allocateFrame()
BufferPool->>BufferPool: findFreeFrame()
BufferPool-->>Test: frameId
```

---

## 6. Concurrent Pin

```mermaid
sequenceDiagram
actor Tx1
actor Tx2
participant BufferPool

Tx1->>BufferPool: getPage(10)
Tx2->>BufferPool: getPage(10)
BufferPool-->>Tx1: Page
BufferPool-->>Tx2: Page
```

---

## 7. Pin Dirty Page

```mermaid
sequenceDiagram
actor Test
participant BufferPool
participant Page

Test->>BufferPool: pinPage(5)
BufferPool->>Page: pin()
Page-->>BufferPool: pinned
BufferPool-->>Test: pinnedPage
```

---

## 8. Unpin Dirty Page

```mermaid
sequenceDiagram
actor Test
participant BufferPool
participant Page

Test->>BufferPool: unpinPage(5)
BufferPool->>Page: unpin()
Page-->>BufferPool: unpinned
BufferPool-->>Test: success
```

---

## 9. Reuse Free Frame

```mermaid
sequenceDiagram
actor Test
participant BufferPool

Test->>BufferPool: reuseFreeFrame()
BufferPool->>BufferPool: selectFreeFrame()
BufferPool-->>Test: frameId
```

---

## 10. Select LRU Victim

```mermaid
sequenceDiagram
actor Test
participant BufferPool

Test->>BufferPool: selectVictim()
BufferPool->>BufferPool: chooseLRU()
BufferPool-->>Test: victimFrame
```

---

## 11. Select Clock Victim

```mermaid
sequenceDiagram
actor Test
participant BufferPool

Test->>BufferPool: selectClockVictim()
BufferPool->>BufferPool: advanceClockHand()
BufferPool-->>Test: victimFrame
```

---

## 12. Allocate New Frame

```mermaid
sequenceDiagram
actor Test
participant BufferPool

Test->>BufferPool: allocateFrame()
BufferPool->>BufferPool: reserveFrame()
BufferPool-->>Test: frameId
```

---

## 13. Release Frame

```mermaid
sequenceDiagram
actor Test
participant BufferPool

Test->>BufferPool: releaseFrame(frameId)
BufferPool->>BufferPool: freeFrame()
BufferPool-->>Test: released
```

---

## 14. Flush Single Page

```mermaid
sequenceDiagram
actor Test
participant BufferPool
participant StorageEngine

Test->>BufferPool: flushPage(pageId)
BufferPool->>StorageEngine: writePage(page)
StorageEngine-->>BufferPool: written
BufferPool-->>Test: flushed
```

---

## 15. Flush All Pages

```mermaid
sequenceDiagram
actor Test
participant BufferPool
participant StorageEngine

Test->>BufferPool: flushAllPages()
BufferPool->>StorageEngine: writeAllDirtyPages()
StorageEngine-->>BufferPool: flushed
BufferPool-->>Test: completed
```

---

## 16. Warm Up Cache

```mermaid
sequenceDiagram
actor Test
participant BufferPool
participant Page

Test->>BufferPool: warmUpCache()
BufferPool->>Page: loadHotPages()
Page-->>BufferPool: loaded
BufferPool-->>Test: ready
```

---

## 17. Evict Clean Page

```mermaid
sequenceDiagram
actor Test
participant BufferPool
participant Page

Test->>BufferPool: evictCleanPage()
BufferPool->>Page: flushIfNeeded()
Page-->>BufferPool: clean
BufferPool-->>Test: evicted
```

---

## 18. Track Hit Ratio

```mermaid
sequenceDiagram
actor Test
participant BufferPool

Test->>BufferPool: trackHitRatio()
BufferPool->>BufferPool: updateMetrics()
BufferPool-->>Test: hitRatio
```

---

## 19. Reset Pool State

```mermaid
sequenceDiagram
actor Test
participant BufferPool

Test->>BufferPool: resetPoolState()
BufferPool->>BufferPool: clearCache()
BufferPool-->>Test: reset
```

---

## 20. Export Pool Snapshot

```mermaid
sequenceDiagram
actor Test
participant BufferPool

Test->>BufferPool: exportSnapshot()
BufferPool->>BufferPool: snapshotState()
BufferPool-->>Test: snapshot
```
