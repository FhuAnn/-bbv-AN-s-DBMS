# DatabaseServer Testing - Main Functional Sequences

---

## 1. Start Server

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant ConfigurationManager

Test->>DatabaseServer: start()
DatabaseServer->>ConfigurationManager: loadConfiguration()
ConfigurationManager-->>DatabaseServer: loaded
DatabaseServer-->>Test: started
```

---

## 2. Stop Server

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant ConfigurationManager

Test->>DatabaseServer: stop()
DatabaseServer->>ConfigurationManager: saveConfiguration()
ConfigurationManager-->>DatabaseServer: saved
DatabaseServer-->>Test: stopped
```

---

## 3. Restart Server

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant ConfigurationManager

Test->>DatabaseServer: restart()
DatabaseServer->>ConfigurationManager: reloadConfiguration()
ConfigurationManager-->>DatabaseServer: reloaded
DatabaseServer-->>Test: restarted
```

---

## 4. Create Database

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant DatabaseManager

Test->>DatabaseServer: createDatabase(name)
DatabaseServer->>DatabaseManager: createDatabase(name)
DatabaseManager-->>DatabaseServer: created
DatabaseServer-->>Test: success
```

---

## 5. Drop Database

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant DatabaseManager

Test->>DatabaseServer: dropDatabase(name)
DatabaseServer->>DatabaseManager: dropDatabase(name)
DatabaseManager-->>DatabaseServer: dropped
DatabaseServer-->>Test: success
```

---

## 6. Open Database

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant DatabaseManager

Test->>DatabaseServer: openDatabase(name)
DatabaseServer->>DatabaseManager: openDatabase(name)
DatabaseManager-->>DatabaseServer: opened
DatabaseServer-->>Test: success
```

---

## 7. Close Database

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant DatabaseManager

Test->>DatabaseServer: closeDatabase(name)
DatabaseServer->>DatabaseManager: closeDatabase(name)
DatabaseManager-->>DatabaseServer: closed
DatabaseServer-->>Test: success
```

---

## 8. Attach Database

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant DatabaseManager

Test->>DatabaseServer: attachDatabase(name)
DatabaseServer->>DatabaseManager: attachDatabase(name)
DatabaseManager-->>DatabaseServer: attached
DatabaseServer-->>Test: success
```

---

## 9. Detach Database

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant DatabaseManager

Test->>DatabaseServer: detachDatabase(name)
DatabaseServer->>DatabaseManager: detachDatabase(name)
DatabaseManager-->>DatabaseServer: detached
DatabaseServer-->>Test: success
```

---

## 10. List Databases

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant DatabaseManager

Test->>DatabaseServer: listDatabases()
DatabaseServer->>DatabaseManager: listDatabases()
DatabaseManager-->>DatabaseServer: list
DatabaseServer-->>Test: list
```

---

## 11. Validate Configuration

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant ConfigurationManager

Test->>DatabaseServer: validateConfiguration()
DatabaseServer->>ConfigurationManager: validateConfiguration()
ConfigurationManager-->>DatabaseServer: valid
DatabaseServer-->>Test: valid
```

---

## 12. Initialize Storage

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant StorageEngine

Test->>DatabaseServer: initializeStorage()
DatabaseServer->>StorageEngine: initialize()
StorageEngine-->>DatabaseServer: ready
DatabaseServer-->>Test: success
```

---

## 13. Load Catalog

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant DatabaseManager

Test->>DatabaseServer: loadCatalog()
DatabaseServer->>DatabaseManager: loadCatalog()
DatabaseManager-->>DatabaseServer: loaded
DatabaseServer-->>Test: success
```

---

## 14. Recover Server

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant RecoveryManager

Test->>DatabaseServer: recover()
DatabaseServer->>RecoveryManager: crashRecovery()
RecoveryManager-->>DatabaseServer: recovered
DatabaseServer-->>Test: success
```

---

## 15. Backup Server State

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant BackupManager

Test->>DatabaseServer: backupState()
DatabaseServer->>BackupManager: fullBackup()
BackupManager-->>DatabaseServer: backupId
DatabaseServer-->>Test: backupId
```

---

## 16. Restore Server State

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant BackupManager

Test->>DatabaseServer: restoreState(backupId)
DatabaseServer->>BackupManager: restoreBackup(backupId)
BackupManager-->>DatabaseServer: restored
DatabaseServer-->>Test: success
```

---

## 17. Health Check

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer

Test->>DatabaseServer: healthCheck()
DatabaseServer->>DatabaseServer: inspectRuntimeState()
DatabaseServer-->>Test: healthy
```

---

## 18. Reload Metadata

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant DatabaseManager

Test->>DatabaseServer: reloadMetadata()
DatabaseServer->>DatabaseManager: reloadCatalog()
DatabaseManager-->>DatabaseServer: reloaded
DatabaseServer-->>Test: success
```

---

## 19. Sync Configuration

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant ConfigurationManager

Test->>DatabaseServer: syncConfiguration()
DatabaseServer->>ConfigurationManager: sync()
ConfigurationManager-->>DatabaseServer: synced
DatabaseServer-->>Test: success
```

---

## 20. Report Status

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer

Test->>DatabaseServer: reportStatus()
DatabaseServer->>DatabaseServer: summarizeServerState()
DatabaseServer-->>Test: status
```
