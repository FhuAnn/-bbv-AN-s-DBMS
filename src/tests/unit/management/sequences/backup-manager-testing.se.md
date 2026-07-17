# BackupManager Testing - Main Functional Sequences

---

## 1. Full Backup

```mermaid
sequenceDiagram
actor Test
participant BackupManager
participant StorageEngine

Test->>BackupManager: fullBackup()
BackupManager->>StorageEngine: readAllPages()
StorageEngine-->>BackupManager: data
BackupManager-->>Test: backupCreated
```

---

## 2. Incremental Backup

```mermaid
sequenceDiagram
actor Test
participant BackupManager
participant WALManager

Test->>BackupManager: incrementalBackup()
BackupManager->>WALManager: getChangedPages()
WALManager-->>BackupManager: delta
BackupManager-->>Test: backupCreated
```

---

## 3. Restore Backup

```mermaid
sequenceDiagram
actor Test
participant BackupManager
participant StorageEngine

Test->>BackupManager: restoreBackup()
BackupManager->>StorageEngine: writePages()
StorageEngine-->>BackupManager: restored
BackupManager-->>Test: success
```

---

## 4. Verify Backup

```mermaid
sequenceDiagram
actor Test
participant BackupManager
participant BackupFile

Test->>BackupManager: verifyBackup()
BackupManager->>BackupFile: checksum()
BackupFile-->>BackupManager: valid
BackupManager-->>Test: success
```

---

## 5. Differential Backup

```mermaid
sequenceDiagram
actor Test
participant BackupManager
participant WALManager

Test->>BackupManager: differentialBackup(backupId)
BackupManager->>WALManager: getChangesSince(backupId)
WALManager-->>BackupManager: delta
BackupManager-->>Test: backupCreated
```

---

## 6. Compress Backup

```mermaid
sequenceDiagram
actor Test
participant BackupManager
participant BackupFile

Test->>BackupManager: compressBackup(backupId)
BackupManager->>BackupFile: compress(backupId)
BackupFile-->>BackupManager: compressed
BackupManager-->>Test: success
```

---

## 7. Encrypt Backup

```mermaid
sequenceDiagram
actor Test
participant BackupManager
participant BackupFile

Test->>BackupManager: encryptBackup(backupId,key)
BackupManager->>BackupFile: encrypt(key)
BackupFile-->>BackupManager: encrypted
BackupManager-->>Test: success
```

---

## 8. Restore From Snapshot

```mermaid
sequenceDiagram
actor Test
participant BackupManager
participant StorageEngine

Test->>BackupManager: restoreFromSnapshot(snapshotId)
BackupManager->>StorageEngine: loadSnapshot(snapshotId)
StorageEngine-->>BackupManager: loaded
BackupManager-->>Test: success
```

---

## 9. List Backups

```mermaid
sequenceDiagram
actor Test
participant BackupManager
participant BackupFile

Test->>BackupManager: listBackups()
BackupManager->>BackupFile: enumerate()
BackupFile-->>BackupManager: backups
BackupManager-->>Test: backups
```

---

## 10. Verify Restore Point

```mermaid
sequenceDiagram
actor Test
participant BackupManager
participant BackupFile

Test->>BackupManager: verifyRestorePoint(pointId)
BackupManager->>BackupFile: checksum(pointId)
BackupFile-->>BackupManager: valid
BackupManager-->>Test: true
```

---

## 11. Archive Backup

```mermaid
sequenceDiagram
actor Test
participant BackupManager
participant BackupFile

Test->>BackupManager: archiveBackup(backupId, archivePath)
BackupManager->>BackupFile: moveToArchive(archivePath)
BackupFile-->>BackupManager: archived
BackupManager-->>Test: success
```

---

## 12. Restore Backup Metadata

```mermaid
sequenceDiagram
actor Test
participant BackupManager
participant StorageEngine

Test->>BackupManager: restoreBackupMetadata(backupId)
BackupManager->>StorageEngine: loadBackupCatalog(backupId)
StorageEngine-->>BackupManager: metadata
BackupManager-->>Test: restored
```

---

## 13. Validate Backup Chain

```mermaid
sequenceDiagram
actor Test
participant BackupManager
participant BackupFile

Test->>BackupManager: validateBackupChain(chainId)
BackupManager->>BackupFile: inspectChain(chainId)
BackupFile-->>BackupManager: valid
BackupManager-->>Test: true
```
