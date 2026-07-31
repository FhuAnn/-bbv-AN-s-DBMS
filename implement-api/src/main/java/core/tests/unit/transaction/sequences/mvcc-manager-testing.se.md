# MVCCManager Testing - Main Functional Sequences

---

## 1. Create Snapshot

```mermaid
sequenceDiagram
actor Test
participant MVCCManager
participant TransactionManager

Test->>MVCCManager: createSnapshot(tx)
MVCCManager->>TransactionManager: getActiveTransactions()
TransactionManager-->>MVCCManager: activeTxIds
MVCCManager->>MVCCManager: assignSnapshotVersion()
MVCCManager-->>Test: Snapshot
```

---

## 2. Read Visible Version

```mermaid
sequenceDiagram
actor Test
participant MVCCManager
participant VersionChain

Test->>MVCCManager: readVisibleVersion(row, snapshot)
MVCCManager->>VersionChain: traverse()
VersionChain-->>MVCCManager: visibleVersion
MVCCManager-->>Test: version
```

---

## 3. Skip Invisible Version

```mermaid
sequenceDiagram
actor Test
participant MVCCManager
participant VersionChain

Test->>MVCCManager: readVisibleVersion(row, snapshot)
MVCCManager->>VersionChain: checkVisibility()
alt Invisible version
    VersionChain-->>MVCCManager: skip
    MVCCManager->>VersionChain: nextVersion()
    VersionChain-->>MVCCManager: visibleVersion
end
MVCCManager-->>Test: visibleVersion
```

---

## 4. Create Version

```mermaid
sequenceDiagram
actor Test
participant MVCCManager
participant VersionChain

Test->>MVCCManager: createVersion(row)
MVCCManager->>VersionChain: appendVersion()
VersionChain-->>MVCCManager: newVersion
MVCCManager-->>Test: versionCreated
```

---

## 5. Garbage Collect

```mermaid
sequenceDiagram
actor System
participant MVCCManager
participant VersionChain

System->>MVCCManager: garbageCollect()
MVCCManager->>VersionChain: removeOldVersions()
VersionChain-->>MVCCManager: removed
MVCCManager-->>System: completed
```

---

## 6. Concurrent Writers

```mermaid
sequenceDiagram
actor Tx1
actor Tx2
participant MVCCManager

Tx1->>MVCCManager: update(row)
Tx2->>MVCCManager: update(row)
MVCCManager-->>Tx1: version1
MVCCManager-->>Tx2: version2
```

---

## 7. Read Snapshot

```mermaid
sequenceDiagram
actor Test
participant MVCCManager
participant VersionChain

Test->>MVCCManager: readSnapshot(tx)
MVCCManager->>VersionChain: captureVisibleState()
VersionChain-->>MVCCManager: snapshot
MVCCManager-->>Test: snapshot
```

---

## 8. Write New Version

```mermaid
sequenceDiagram
actor Test
participant MVCCManager
participant VersionChain

Test->>MVCCManager: writeVersion(row)
MVCCManager->>VersionChain: appendNewVersion()
VersionChain-->>MVCCManager: appended
MVCCManager-->>Test: newVersion
```

---

## 9. Prune Old Versions

```mermaid
sequenceDiagram
actor System
participant MVCCManager
participant VersionChain

System->>MVCCManager: pruneOldVersions()
MVCCManager->>VersionChain: removeStaleVersions()
VersionChain-->>MVCCManager: pruned
MVCCManager-->>System: success
```

---

## 10. Check Read Visibility

```mermaid
sequenceDiagram
actor Test
participant MVCCManager
participant VersionChain

Test->>MVCCManager: checkReadVisibility(row,snapshot)
MVCCManager->>VersionChain: isVisible()
VersionChain-->>MVCCManager: visible
MVCCManager-->>Test: true
```

---

## 11. Check Write Visibility

```mermaid
sequenceDiagram
actor Test
participant MVCCManager
participant VersionChain

Test->>MVCCManager: checkWriteVisibility(row,tx)
MVCCManager->>VersionChain: canWrite()
VersionChain-->>MVCCManager: allowed
MVCCManager-->>Test: true
```

---

## 12. Vacuum Versions

```mermaid
sequenceDiagram
actor System
participant MVCCManager
participant VersionChain

System->>MVCCManager: vacuumVersions()
MVCCManager->>VersionChain: cleanGarbage()
VersionChain-->>MVCCManager: cleaned
MVCCManager-->>System: success
```

---

## 13. Merge Version Chain

```mermaid
sequenceDiagram
actor Test
participant MVCCManager
participant VersionChain

Test->>MVCCManager: mergeVersionChain()
MVCCManager->>VersionChain: compact()
VersionChain-->>MVCCManager: merged
MVCCManager-->>Test: success
```

---

## 14. Allocate Visibility Range

```mermaid
sequenceDiagram
actor Test
participant MVCCManager
participant TransactionManager

Test->>MVCCManager: allocateVisibilityRange()
MVCCManager->>TransactionManager: getActiveTransactions()
TransactionManager-->>MVCCManager: range
MVCCManager-->>Test: range
```

---

## 15. Resolve Conflict

```mermaid
sequenceDiagram
actor Test
participant MVCCManager
participant VersionChain

Test->>MVCCManager: resolveConflict(row)
MVCCManager->>VersionChain: compareVersions()
VersionChain-->>MVCCManager: resolved
MVCCManager-->>Test: success
```

---

## 16. Commit Snapshot

```mermaid
sequenceDiagram
actor Test
participant MVCCManager
participant TransactionManager

Test->>MVCCManager: commitSnapshot(tx)
MVCCManager->>TransactionManager: confirmSnapshot()
TransactionManager-->>MVCCManager: confirmed
MVCCManager-->>Test: success
```

---

## 17. Rollback Version

```mermaid
sequenceDiagram
actor Test
participant MVCCManager
participant VersionChain

Test->>MVCCManager: rollbackVersion(row)
MVCCManager->>VersionChain: revertToPrevious()
VersionChain-->>MVCCManager: reverted
MVCCManager-->>Test: success
```

---

## 18. Pin Version

```mermaid
sequenceDiagram
actor Test
participant MVCCManager
participant VersionChain

Test->>MVCCManager: pinVersion(row)
MVCCManager->>VersionChain: pin()
VersionChain-->>MVCCManager: pinned
MVCCManager-->>Test: success
```

---

## 19. Unpin Version

```mermaid
sequenceDiagram
actor Test
participant MVCCManager
participant VersionChain

Test->>MVCCManager: unpinVersion(row)
MVCCManager->>VersionChain: unpin()
VersionChain-->>MVCCManager: unpinned
MVCCManager-->>Test: success
```

---

## 20. Export Version History

```mermaid
sequenceDiagram
actor Test
participant MVCCManager
participant VersionChain

Test->>MVCCManager: exportHistory(row)
MVCCManager->>VersionChain: listVersions()
VersionChain-->>MVCCManager: history
MVCCManager-->>Test: history
```
