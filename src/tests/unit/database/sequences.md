# DatabaseServer - Main Unit Test Sequences

---

## 1. Constructor Initialization

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant ConfigurationManager
participant StorageEngine
participant CatalogManager
participant TransactionManager
participant SecurityManager

Test->>DatabaseServer: new DatabaseServer()

activate DatabaseServer

DatabaseServer->>ConfigurationManager: initialize()
ConfigurationManager-->>DatabaseServer: ready

DatabaseServer->>StorageEngine: initialize()
StorageEngine-->>DatabaseServer: ready

DatabaseServer->>CatalogManager: initialize()
CatalogManager-->>DatabaseServer: ready

DatabaseServer->>TransactionManager: initialize()
TransactionManager-->>DatabaseServer: ready

DatabaseServer->>SecurityManager: initialize()
SecurityManager-->>DatabaseServer: ready

DatabaseServer-->>Test: DatabaseServer
deactivate DatabaseServer
```

---

## 2. Start Server

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant StorageEngine
participant BufferPool
participant RecoveryManager

Test->>DatabaseServer: start()

DatabaseServer->>StorageEngine: start()
StorageEngine-->>DatabaseServer: ready

DatabaseServer->>BufferPool: warmup()
BufferPool-->>DatabaseServer: completed

DatabaseServer->>RecoveryManager: recoverIfNeeded()
RecoveryManager-->>DatabaseServer: recovered

DatabaseServer-->>Test: RUNNING
```

---

## 3. Shutdown Server

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant BufferPool
participant WALManager
participant StorageEngine

Test->>DatabaseServer: shutdown()

DatabaseServer->>BufferPool: flushDirtyPages()
BufferPool-->>DatabaseServer: completed

DatabaseServer->>WALManager: flush()
WALManager-->>DatabaseServer: completed

DatabaseServer->>StorageEngine: stop()
StorageEngine-->>DatabaseServer: stopped

DatabaseServer-->>Test: STOPPED
```

---

## 4. Create Database

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant DatabaseManager
participant CatalogManager
participant StorageEngine

Test->>DatabaseServer: createDatabase("School")

DatabaseServer->>DatabaseManager: create()

DatabaseManager->>StorageEngine: createFiles()
StorageEngine-->>DatabaseManager: success

DatabaseManager->>CatalogManager: registerMetadata()
CatalogManager-->>DatabaseManager: success

DatabaseManager-->>DatabaseServer: Database

DatabaseServer-->>Test: Database
```

---

## 5. Drop Database

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant DatabaseManager
participant CatalogManager
participant StorageEngine

Test->>DatabaseServer: dropDatabase()

DatabaseServer->>DatabaseManager: drop()

DatabaseManager->>CatalogManager: removeMetadata()

CatalogManager-->>DatabaseManager: removed

DatabaseManager->>StorageEngine: deleteFiles()

StorageEngine-->>DatabaseManager: deleted

DatabaseManager-->>DatabaseServer: success

DatabaseServer-->>Test: OK
```

---

## 6. Crash Recovery

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant RecoveryManager
participant WALManager
participant CatalogManager

Test->>DatabaseServer: recover()

DatabaseServer->>RecoveryManager: recover()

RecoveryManager->>WALManager: replayLogs()

WALManager-->>RecoveryManager: completed

RecoveryManager->>CatalogManager: restoreMetadata()

CatalogManager-->>RecoveryManager: restored

RecoveryManager-->>DatabaseServer: recovered

DatabaseServer-->>Test: SUCCESS
```

---

## 7. Invalid Configuration

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant ConfigurationManager

Test->>DatabaseServer: start()

DatabaseServer->>ConfigurationManager: validate()

ConfigurationManager-->>DatabaseServer: invalid

DatabaseServer-->>Test: InvalidConfigurationException
```

---

## 8. Concurrent Database Creation

```mermaid
sequenceDiagram
actor Thread1
actor Thread2
participant DatabaseServer
participant DatabaseManager

Thread1->>DatabaseServer: createDatabase()

Thread2->>DatabaseServer: createDatabase()

DatabaseServer->>DatabaseManager: synchronizedCreate()

DatabaseManager-->>Thread1: success

DatabaseManager-->>Thread2: DuplicateDatabaseException
```

---

## 9. Restart Server

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer

Test->>DatabaseServer: restart()

DatabaseServer->>DatabaseServer: shutdown()

DatabaseServer->>DatabaseServer: start()

DatabaseServer-->>Test: RUNNING
```

---

## 10. Full Startup Integration

```mermaid
sequenceDiagram
actor Test
participant DatabaseServer
participant ConfigurationManager
participant StorageEngine
participant BufferPool
participant WALManager
participant RecoveryManager
participant CatalogManager
participant TransactionManager
participant SecurityManager

Test->>DatabaseServer: start()

DatabaseServer->>ConfigurationManager: load()

DatabaseServer->>StorageEngine: start()

StorageEngine->>BufferPool: initialize()

StorageEngine->>WALManager: initialize()

DatabaseServer->>RecoveryManager: recover()

DatabaseServer->>CatalogManager: loadMetadata()

DatabaseServer->>TransactionManager: initialize()

DatabaseServer->>SecurityManager: initialize()

DatabaseServer-->>Test: RUNNING
```