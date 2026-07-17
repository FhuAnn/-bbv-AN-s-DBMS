# LockManager Testing - Main Functional Sequences

---

## 1. Acquire Shared Lock

```mermaid
sequenceDiagram
actor Tx
participant LockManager
participant LockTable

Tx->>LockManager: acquireShared(resource)

LockManager->>LockTable: checkCompatibility()

LockTable-->>LockManager: compatible

LockManager->>LockTable: grantLock()

LockTable-->>Tx: SharedLock
```

---

## 2. Acquire Exclusive Lock

```mermaid
sequenceDiagram
actor Tx
participant LockManager
participant LockTable

Tx->>LockManager: acquireExclusive(resource)

LockManager->>LockTable: checkCompatibility()

LockTable-->>LockManager: compatible

LockManager->>LockTable: grantLock()

LockTable-->>Tx: ExclusiveLock
```

---

## 3. Upgrade Lock

```mermaid
sequenceDiagram
actor Tx
participant LockManager
participant LockTable

Tx->>LockManager: upgradeLock()

LockManager->>LockTable: verifyExclusive()

LockTable-->>LockManager: granted

LockManager-->>Tx: ExclusiveLock
```

---

## 4. Downgrade Lock

```mermaid
sequenceDiagram
actor Tx
participant LockManager
participant LockTable

Tx->>LockManager: downgradeLock()

LockManager->>LockTable: convertToShared()

LockTable-->>Tx: SharedLock
```

---

## 5. Release Lock

```mermaid
sequenceDiagram
actor Tx
participant LockManager
participant LockTable

Tx->>LockManager: release(resource)

LockManager->>LockTable: removeLock()

LockTable-->>LockManager: released

LockManager-->>Tx: success
```

---

## 6. Release All Locks

```mermaid
sequenceDiagram
actor Tx
participant LockManager
participant LockTable

Tx->>LockManager: releaseAll()

LockManager->>LockTable: releaseTransactionLocks()

LockTable-->>LockManager: completed

LockManager-->>Tx: success
```

---

## 7. Detect Deadlock

```mermaid
sequenceDiagram
participant LockManager
participant DeadlockDetector

LockManager->>DeadlockDetector: detect()

DeadlockDetector->>DeadlockDetector: buildWaitForGraph()

DeadlockDetector-->>LockManager: victim

LockManager-->>DeadlockDetector: abortVictim()
```

---

## 8. Lock Timeout

```mermaid
sequenceDiagram
actor Tx
participant LockManager

Tx->>LockManager: acquire()

LockManager->>LockManager: wait()

LockManager-->>Tx: LockTimeoutException
```

---

## 9. Concurrent Readers

```mermaid
sequenceDiagram
actor Tx1
actor Tx2

participant LockManager

Tx1->>LockManager: sharedLock()

Tx2->>LockManager: sharedLock()

LockManager-->>Tx1: granted

LockManager-->>Tx2: granted
```

---

## 10. Concurrent Writers

```mermaid
sequenceDiagram
actor Tx1
actor Tx2

participant LockManager

Tx1->>LockManager: exclusiveLock()

Tx2->>LockManager: exclusiveLock()

LockManager-->>Tx1: granted

LockManager-->>Tx2: waiting
```

---

## 11. Mixed Locks

```mermaid
sequenceDiagram
actor Reader
actor Writer

participant LockManager

Reader->>LockManager: sharedLock()

LockManager-->>Reader: granted

Writer->>LockManager: exclusiveLock()

LockManager-->>Writer: waiting
```

---

## 12. Queue Waiting Transaction

```mermaid
sequenceDiagram
actor Tx
participant LockManager
participant LockTable

Tx->>LockManager: acquire(resource)
LockManager->>LockTable: enqueueWaiter()
LockTable-->>LockManager: queued
LockManager-->>Tx: waiting
```

---

## 13. Wake Waiting Transaction

```mermaid
sequenceDiagram
actor Tx
participant LockManager
participant LockTable

Tx->>LockManager: release(resource)
LockManager->>LockTable: wakeNextWaiter()
LockTable-->>LockManager: woken
LockManager-->>Tx: granted
```

---

## 14. Validate Compatibility Matrix

```mermaid
sequenceDiagram
actor Test
participant LockManager
participant LockTable

Test->>LockManager: validateCompatibility(shared,exclusive)
LockManager->>LockTable: inspectMatrix()
LockTable-->>LockManager: incompatible
LockManager-->>Test: false
```

---

## 15. Select Deadlock Victim

```mermaid
sequenceDiagram
participant LockManager
participant DeadlockDetector

LockManager->>DeadlockDetector: selectVictim()
DeadlockDetector-->>LockManager: victimTx
LockManager-->>DeadlockDetector: abortVictim()
```

---

## 16. Release By Resource

```mermaid
sequenceDiagram
actor Test
participant LockManager
participant LockTable

Test->>LockManager: release(resource)
LockManager->>LockTable: removeResourceLock()
LockTable-->>LockManager: removed
LockManager-->>Test: success
```

---

## 17. Release By Transaction

```mermaid
sequenceDiagram
actor Test
participant LockManager
participant LockTable

Test->>LockManager: releaseAll(tx)
LockManager->>LockTable: removeTransactionLocks()
LockTable-->>LockManager: removed
LockManager-->>Test: success
```

---

## 18. Lock Timeout Retry

```mermaid
sequenceDiagram
actor Tx
participant LockManager

Tx->>LockManager: acquire(resource)
LockManager->>LockManager: waitWithTimeout()
LockManager-->>Tx: retryLater
```

---

## 19. Clean Lock Table

```mermaid
sequenceDiagram
actor Test
participant LockManager
participant LockTable

Test->>LockManager: cleanup()
LockManager->>LockTable: purgeReleasedLocks()
LockTable-->>LockManager: cleaned
LockManager-->>Test: success
```

---

## 20. Resolve Lock Priority

```mermaid
sequenceDiagram
actor Test
participant LockManager
participant LockTable

Test->>LockManager: resolvePriority(tx)
LockManager->>LockTable: compareWaitOrder()
LockTable-->>LockManager: priorityGranted
LockManager-->>Test: granted
```
