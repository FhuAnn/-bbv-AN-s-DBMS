# MonitoringManager Testing - Main Functional Sequences

---

## 1. Collect Metrics

```mermaid
sequenceDiagram
actor Test
participant MonitoringManager
participant DatabaseServer

Test->>MonitoringManager: collectMetrics()
MonitoringManager->>DatabaseServer: readCpuMemoryIo()
DatabaseServer-->>MonitoringManager: metrics
MonitoringManager-->>Test: metrics
```

---

## 2. Detect Slow Query

```mermaid
sequenceDiagram
actor Test
participant MonitoringManager
participant QueryExecutor

Test->>MonitoringManager: detectSlowQuery()
MonitoringManager->>QueryExecutor: inspectLatency()
QueryExecutor-->>MonitoringManager: slowQuery
MonitoringManager-->>Test: alert
```

---

## 3. Raise Alert

```mermaid
sequenceDiagram
actor Test
participant MonitoringManager
participant AlertChannel

Test->>MonitoringManager: raiseAlert()
MonitoringManager->>AlertChannel: notify()
AlertChannel-->>MonitoringManager: sent
MonitoringManager-->>Test: success
```

---

## 4. Capture Deadlock

```mermaid
sequenceDiagram
actor Test
participant MonitoringManager
participant LockManager

Test->>MonitoringManager: captureDeadlock(resourceId)
MonitoringManager->>LockManager: inspectWaitGraph(resourceId)
LockManager-->>MonitoringManager: deadlockInfo
MonitoringManager-->>Test: alert
```

---

## 5. Capture Blocking

```mermaid
sequenceDiagram
actor Test
participant MonitoringManager
participant LockManager

Test->>MonitoringManager: captureBlocking(txId)
MonitoringManager->>LockManager: inspectBlocking(txId)
LockManager-->>MonitoringManager: blockingInfo
MonitoringManager-->>Test: alert
```

---

## 6. Export Metrics

```mermaid
sequenceDiagram
actor Test
participant MonitoringManager
participant MetricExporter

Test->>MonitoringManager: exportMetrics(format)
MonitoringManager->>MetricExporter: export(format)
MetricExporter-->>MonitoringManager: exported
MonitoringManager-->>Test: success
```

---

## 7. Refresh Thresholds

```mermaid
sequenceDiagram
actor Test
participant MonitoringManager
participant AlertChannel

Test->>MonitoringManager: refreshThresholds(cpuLimit,memoryLimit)
MonitoringManager->>AlertChannel: updateThresholds(cpuLimit,memoryLimit)
AlertChannel-->>MonitoringManager: updated
MonitoringManager-->>Test: success
```

---

## 8. Start Collector

```mermaid
sequenceDiagram
actor Test
participant MonitoringManager

Test->>MonitoringManager: startCollector(intervalMs)
MonitoringManager->>MonitoringManager: scheduleCollection(intervalMs)
MonitoringManager-->>Test: started
```

---

## 9. Stop Collector

```mermaid
sequenceDiagram
actor Test
participant MonitoringManager

Test->>MonitoringManager: stopCollector()
MonitoringManager->>MonitoringManager: cancelSchedule()
MonitoringManager-->>Test: stopped
```

---

## 10. Report Health

```mermaid
sequenceDiagram
actor Test
participant MonitoringManager
participant DatabaseServer

Test->>MonitoringManager: reportHealth(serverId)
MonitoringManager->>DatabaseServer: getHealth(serverId)
DatabaseServer-->>MonitoringManager: health
MonitoringManager-->>Test: health
```

---

## 11. Sample Query Latency

```mermaid
sequenceDiagram
actor Test
participant MonitoringManager
participant QueryExecutor

Test->>MonitoringManager: sampleQueryLatency(sql)
MonitoringManager->>QueryExecutor: inspectLatency(sql)
QueryExecutor-->>MonitoringManager: latency
MonitoringManager-->>Test: latency
```

---

## 12. Sample Transaction Rate

```mermaid
sequenceDiagram
actor Test
participant MonitoringManager
participant TransactionManager

Test->>MonitoringManager: sampleTransactionRate(window)
MonitoringManager->>TransactionManager: countCommitted(window)
TransactionManager-->>MonitoringManager: rate
MonitoringManager-->>Test: rate
```

---

## 13. Export Alert History

```mermaid
sequenceDiagram
actor Test
participant MonitoringManager
participant AlertChannel

Test->>MonitoringManager: exportAlertHistory(range)
MonitoringManager->>AlertChannel: getEvents(range)
AlertChannel-->>MonitoringManager: events
MonitoringManager-->>Test: events
```
