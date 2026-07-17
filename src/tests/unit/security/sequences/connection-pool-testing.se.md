# ConnectionPool Testing - Main Functional Sequences

---

## 1. Acquire Connection

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant ConnectionFactory
participant Connection

Test->>ConnectionPool: acquireConnection()
ConnectionPool->>ConnectionFactory: createOrReuse()
ConnectionFactory->>Connection: open()
Connection-->>ConnectionFactory: ready
ConnectionFactory-->>ConnectionPool: connection
ConnectionPool-->>Test: connection
```

---

## 2. Acquire Warm Connection

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant IdleList
participant Connection

Test->>ConnectionPool: acquireWarmConnection()
ConnectionPool->>IdleList: pollWarmConnection()
IdleList-->>ConnectionPool: connection
ConnectionPool->>Connection: markInUse()
Connection-->>ConnectionPool: active
ConnectionPool-->>Test: connection
```

---

## 3. Create New Connection

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant ConnectionFactory
participant Connection

Test->>ConnectionPool: createConnection()
ConnectionPool->>ConnectionFactory: openFreshConnection()
ConnectionFactory->>Connection: connect()
Connection-->>ConnectionFactory: connected
ConnectionFactory-->>ConnectionPool: connection
ConnectionPool-->>Test: connection
```

---

## 4. Validate Borrowed Connection

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant ConnectionValidator
participant Connection

Test->>ConnectionPool: validateBorrowedConnection(conn)
ConnectionPool->>ConnectionValidator: validate(conn)
ConnectionValidator->>Connection: ping()
Connection-->>ConnectionValidator: alive
ConnectionValidator-->>ConnectionPool: valid
ConnectionPool-->>Test: valid
```

---

## 5. Release Connection

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant Connection
participant IdleList

Test->>ConnectionPool: releaseConnection(conn)
ConnectionPool->>Connection: resetSessionState()
Connection-->>ConnectionPool: reset
ConnectionPool->>IdleList: add(conn)
IdleList-->>ConnectionPool: stored
ConnectionPool-->>Test: success
```

---

## 6. Recycle Connection

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant Connection
participant IdleList

Test->>ConnectionPool: recycleConnection(conn)
ConnectionPool->>Connection: closeTransactionState()
Connection-->>ConnectionPool: closed
ConnectionPool->>IdleList: push(conn)
IdleList-->>ConnectionPool: recycled
ConnectionPool-->>Test: success
```

---

## 7. Evict Idle Connection

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant IdleList
participant Connection

Test->>ConnectionPool: evictIdleConnection()
ConnectionPool->>IdleList: pollExpired()
IdleList-->>ConnectionPool: connection
ConnectionPool->>Connection: close()
Connection-->>ConnectionPool: closed
ConnectionPool-->>Test: evicted
```

---

## 8. Cleanup Expired Connections

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant IdleList
participant Scheduler

Test->>ConnectionPool: cleanupExpiredConnections()
ConnectionPool->>Scheduler: scheduleCleanup()
Scheduler->>IdleList: removeExpired()
IdleList-->>Scheduler: removed
Scheduler-->>ConnectionPool: done
ConnectionPool-->>Test: cleaned
```

---

## 9. Ping Connection

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant Connection

Test->>ConnectionPool: pingConnection(conn)
ConnectionPool->>Connection: ping()
Connection-->>ConnectionPool: alive
ConnectionPool-->>Test: alive
```

---

## 10. Reset Session State

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant Connection

Test->>ConnectionPool: resetSessionState(conn)
ConnectionPool->>Connection: resetSession()
Connection-->>ConnectionPool: reset
ConnectionPool-->>Test: success
```

---

## 11. Mark Connection Busy

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant Connection

Test->>ConnectionPool: markConnectionBusy(conn)
ConnectionPool->>Connection: setBusy(true)
Connection-->>ConnectionPool: busy
ConnectionPool-->>Test: marked
```

---

## 12. Mark Connection Idle

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant Connection

Test->>ConnectionPool: markConnectionIdle(conn)
ConnectionPool->>Connection: setBusy(false)
Connection-->>ConnectionPool: idle
ConnectionPool-->>Test: marked
```

---

## 13. Resize Pool Up

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant ConnectionFactory
participant IdleList

Test->>ConnectionPool: resizePoolUp(targetSize)
ConnectionPool->>ConnectionFactory: openAdditionalConnections()
ConnectionFactory-->>ConnectionPool: connections
ConnectionPool->>IdleList: append(connections)
IdleList-->>ConnectionPool: appended
ConnectionPool-->>Test: resized
```

---

## 14. Shrink Pool Down

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant IdleList
participant Connection

Test->>ConnectionPool: shrinkPoolDown(targetSize)
ConnectionPool->>IdleList: selectSurplus()
IdleList-->>ConnectionPool: connections
ConnectionPool->>Connection: close()
Connection-->>ConnectionPool: closed
ConnectionPool-->>Test: resized
```

---

## 15. Borrow With Timeout

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant WaitQueue
participant Connection

Test->>ConnectionPool: borrowWithTimeout(timeout)
ConnectionPool->>WaitQueue: waitForConnection()
WaitQueue-->>ConnectionPool: connection
ConnectionPool->>Connection: markInUse()
Connection-->>ConnectionPool: active
ConnectionPool-->>Test: connection
```

---

## 16. Recover Broken Connection

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant ConnectionFactory
participant Connection

Test->>ConnectionPool: recoverBrokenConnection(conn)
ConnectionPool->>Connection: closeBroken()
Connection-->>ConnectionPool: closed
ConnectionPool->>ConnectionFactory: reopen()
ConnectionFactory->>Connection: connect()
ConnectionFactory-->>ConnectionPool: connection
ConnectionPool-->>Test: recovered
```

---

## 17. Replenish Pool

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant ConnectionFactory
participant IdleList

Test->>ConnectionPool: replenishPool()
ConnectionPool->>ConnectionFactory: createBatch()
ConnectionFactory-->>ConnectionPool: connections
ConnectionPool->>IdleList: store(connections)
IdleList-->>ConnectionPool: stored
ConnectionPool-->>Test: replenished
```

---

## 18. Refresh Authentication

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant Connection
participant AuthToken

Test->>ConnectionPool: refreshAuthentication(conn)
ConnectionPool->>Connection: refreshCredentialContext()
Connection->>AuthToken: renew()
AuthToken-->>Connection: renewed
Connection-->>ConnectionPool: refreshed
ConnectionPool-->>Test: success
```

---

## 19. Report Metrics

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant MetricsCollector

Test->>ConnectionPool: reportMetrics()
ConnectionPool->>MetricsCollector: collectPoolStats()
MetricsCollector-->>ConnectionPool: stats
ConnectionPool-->>Test: stats
```

---

## 20. Shutdown Pool

```mermaid
sequenceDiagram
actor Test
participant ConnectionPool
participant IdleList
participant Connection

Test->>ConnectionPool: shutdownPool()
ConnectionPool->>IdleList: drainAll()
IdleList-->>ConnectionPool: connections
ConnectionPool->>Connection: close()
Connection-->>ConnectionPool: closed
ConnectionPool-->>Test: shutdownComplete
```
