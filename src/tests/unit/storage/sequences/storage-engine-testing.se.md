# StorageEngine Testing - Main Functional Sequences

---

## 1. Allocate Page

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant DiskManager

Test->>StorageEngine: allocatePage()
StorageEngine->>DiskManager: allocateNewPage(file)
DiskManager-->>StorageEngine: pageId
StorageEngine-->>Test: Page
```

---

## 2. Read Page

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant DiskManager
participant Page

Test->>StorageEngine: readPage(pageId)
StorageEngine->>DiskManager: readPage(file, pageId)
DiskManager-->>StorageEngine: Page data
StorageEngine->>Page: new Page(pageId, data)
Page-->>StorageEngine: Page
StorageEngine-->>Test: Page
```

---

## 3. Write Page

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant DiskManager
participant Page

Test->>StorageEngine: writePage(page)
StorageEngine->>DiskManager: writePage(file, pageId, page)
DiskManager-->>StorageEngine: written
StorageEngine-->>Test: success
```

---

## 4. Split Page

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant Page

Test->>StorageEngine: splitPage(page)
StorageEngine->>Page: split()
Page-->>StorageEngine: left/right pages
StorageEngine-->>Test: splitResult
```

---

## 5. Compress Page

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant Page

Test->>StorageEngine: compressPage(page)
StorageEngine->>Page: compress()
Page-->>StorageEngine: compressed
StorageEngine-->>Test: success
```

---

## 6. Encrypt Page

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant Page

Test->>StorageEngine: encryptPage(page)
StorageEngine->>Page: encrypt()
Page-->>StorageEngine: encrypted
StorageEngine-->>Test: success
```

---

## 7. Free Page

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant DiskManager

Test->>StorageEngine: freePage(pageId)
StorageEngine->>DiskManager: releasePage(file, pageId)
DiskManager-->>StorageEngine: released
StorageEngine-->>Test: success
```

---

## 8. Merge Page

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant Page

Test->>StorageEngine: mergePage(left,right)
StorageEngine->>Page: merge()
Page-->>StorageEngine: mergedPage
StorageEngine-->>Test: mergedPage
```

---

## 9. Flush Dirty Pages

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant DiskManager
participant Page

Test->>StorageEngine: flushDirtyPages()
StorageEngine->>Page: getDirtyPages()
Page-->>StorageEngine: pages
StorageEngine->>DiskManager: writePageBatch()
DiskManager-->>StorageEngine: flushed
StorageEngine-->>Test: success
```

---

## 10. Checkpoint Storage

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant DiskManager

Test->>StorageEngine: checkpoint()
StorageEngine->>DiskManager: createCheckpoint()
DiskManager-->>StorageEngine: checkpointId
StorageEngine-->>Test: checkpointId
```

---

## 11. Allocate Extent

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant DiskManager

Test->>StorageEngine: allocateExtent()
StorageEngine->>DiskManager: allocateExtent()
DiskManager-->>StorageEngine: extentId
StorageEngine-->>Test: extentId
```

---

## 12. Release Extent

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant DiskManager

Test->>StorageEngine: releaseExtent(extentId)
StorageEngine->>DiskManager: freeExtent(extentId)
DiskManager-->>StorageEngine: freed
StorageEngine-->>Test: success
```

---

## 13. Recover Page

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant WALManager
participant Page

Test->>StorageEngine: recoverPage(pageId)
StorageEngine->>WALManager: replayPage(pageId)
WALManager-->>StorageEngine: data
StorageEngine->>Page: restore(data)
Page-->>StorageEngine: restored
StorageEngine-->>Test: success
```

---

## 14. Validate Checksum

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant Page

Test->>StorageEngine: validateChecksum(page)
StorageEngine->>Page: computeChecksum()
Page-->>StorageEngine: checksum
StorageEngine-->>Test: valid
```

---

## 15. Pin Page

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant Page

Test->>StorageEngine: pinPage(pageId)
StorageEngine->>Page: pin()
Page-->>StorageEngine: pinned
StorageEngine-->>Test: success
```

---

## 16. Unpin Page

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant Page

Test->>StorageEngine: unpinPage(pageId)
StorageEngine->>Page: unpin()
Page-->>StorageEngine: unpinned
StorageEngine-->>Test: success
```

---

## 17. Map Logical To Physical

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant DiskManager

Test->>StorageEngine: mapLogicalToPhysical(pageId)
StorageEngine->>DiskManager: resolveLocation(pageId)
DiskManager-->>StorageEngine: location
StorageEngine-->>Test: location
```

---

## 18. Copy On Write Page

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant Page

Test->>StorageEngine: copyOnWrite(page)
StorageEngine->>Page: clone()
Page-->>StorageEngine: clonedPage
StorageEngine-->>Test: clonedPage
```

---

## 19. Archive Page

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant DiskManager

Test->>StorageEngine: archivePage(pageId)
StorageEngine->>DiskManager: archive(pageId)
DiskManager-->>StorageEngine: archived
StorageEngine-->>Test: success
```

---

## 20. Restore Page

```mermaid
sequenceDiagram
actor Test
participant StorageEngine
participant DiskManager
participant Page

Test->>StorageEngine: restorePage(pageId)
StorageEngine->>DiskManager: restore(pageId)
DiskManager-->>StorageEngine: data
StorageEngine->>Page: rebuild(data)
Page-->>StorageEngine: restoredPage
StorageEngine-->>Test: restoredPage
```
